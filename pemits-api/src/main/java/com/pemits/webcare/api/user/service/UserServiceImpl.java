package com.pemits.webcare.api.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.doctor.service.DoctorService;
import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.patient.service.PatientService;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.repository.UserRepository;
import com.pemits.webcare.api.user.repository.spec.UserSpecBuilder;
import com.pemits.webcare.core.constant.EmailConstant.Template;
import com.pemits.webcare.core.dto.EmailDto;
import com.pemits.webcare.core.entity.AppUser;
import com.pemits.webcare.core.enums.Status;
import com.pemits.webcare.core.enums.UserType;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import com.pemits.webcare.core.service.EmailService;
import com.pemits.webcare.core.utils.PasswordGenerator;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Value("${pemits.url}")
    private String webUrl;

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    protected UserServiceImpl(
        UserRepository repository,
        PasswordEncoder passwordEncoder,
        EmailService emailService,
        DoctorService doctorService,
        PatientService patientService) {
        super(repository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Transactional
    @Override
    public User save(User user) {
        String password = null;
        if (user.getId() == null) {
            // generate password in case of new user.
            PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
            password = passwordGenerator.generate(10);

            user.setPassword(passwordEncoder.encode(password));
            user.setStatus(Status.ACTIVE);
        } else {
            user.setPassword(repository.getOne(user.getId()).getPassword());
        }

        User saved = repository.save(user);

        // in case of new user
        if (password != null) {
            // create associate entity
            AppUser appUser = null;
            switch (saved.getUserType()) {
                case DOCTOR:
                    Doctor doctor = new Doctor();
                    doctor.setUser(saved);
                    appUser = saveAppUser(doctor);
                    break;
                case PATIENT:
                    Patient patient = new Patient();
                    patient.setUser(saved);
                    appUser = saveAppUser(patient);
                    break;
            }

            // send email
            EmailDto emailDto = EmailDto.builder()
                .to(user.getEmail())
                .toName(user.getName())
                .username(user.getUsername())
                .password(password)
                .webUrl(webUrl)
                .build();

            if (saved.getUserType().equals(UserType.PATIENT)) {
                emailDto.setPatientId(String.valueOf(((Patient) appUser).getId()));
                emailDto.setTemplate(Template.PATIENT_REGISTRATION);
            } else {
                emailDto.setTemplate(Template.REGISTRATION_CREDENTIALS);
            }
            emailService.send(emailDto);
        }

        return saved;
    }

    @Override
    protected BaseSpecBuilder<User> getSpec(Map<String, String> filterParams) {
        return new UserSpecBuilder(filterParams);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            user = this.findByUsername(user.getUsername());
            return user;
        } else {
            throw new UsernameNotFoundException(
                "User is not authenticated; Found of type " + authentication.getPrincipal()
                    .getClass() + "; Expected type User");
        }
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        AppUser savedAppUser;
        switch (appUser.getUserType()) {
            case DOCTOR:
                savedAppUser = doctorService.save((Doctor) appUser);
                break;
            case PATIENT:
                savedAppUser = patientService.save((Patient) appUser);
                break;
            default:
                savedAppUser = null;
        }
        return savedAppUser;
    }

}
