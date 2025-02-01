package com.crm.CRM.service;

import com.crm.CRM.entity.User;
import com.crm.CRM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
       if(user.getEmail() ==null || user.getEmail().isEmpty()){
           throw new IllegalArgumentException("Email is required");
       }
       if(user.getPassword() ==null || user.getPassword().isEmpty()){
           throw new IllegalArgumentException("Password is required");
       }
         user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setCreatedAt(LocalDateTime.now());
       user.setUpdatedAt(LocalDateTime.now());

       return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
   public void softDeleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
   }

   public void resetPassword(Long id, String newPassword){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
   }
   public void incrementFailedLoginAttempts(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));

        int attempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(attempts);

        if(attempts >= 10) {
            user.setLocked(true);
        }
        userRepository.save(user);
   }
    public boolean checkIfLocked(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        return user.isLocked();
    }

    public boolean checkRole(Long id, String requiredRole) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        return user.getRole().name().equalsIgnoreCase(requiredRole);
    }


    public boolean checkPermissions(Long id, String requiredPermission) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        return user.getPermissions().contains(requiredPermission);
    }

    public void updateSubscriptionPlan(Long id, String newSubscriptionPlan) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        user.setSubscriptionPlan(newSubscriptionPlan);
        userRepository.save(user);
    }

    public void updateNotificationPreferences(Long id, String newPreferences) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        user.setNotificationPreferences(newPreferences);
        userRepository.save(user);
    }


    public boolean isValidEmail(String email) {
        // Add more complex email validation here if necessary
        return email != null && email.contains("@");
    }


    private void validateUserExists(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
    }



    public User updateUser(Long id, User userdetails) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
        user.setFirstName(userdetails.getFirstName());
        user.setLastName(userdetails.getLastName());
        user.setProfileImageUrl(userdetails.getProfileImageUrl());
        user.setDateOfBirth(userdetails.getDateOfBirth());
        user.setGender(userdetails.getGender());
        user.setAddress(userdetails.getAddress());
        user.setCity(userdetails.getCity());
        user.setState(userdetails.getState());
        user.setCountry(userdetails.getCountry());
        user.setZipCode(userdetails.getZipCode());
        user.setCompanyName(userdetails.getCompanyName());
        user.setJobTitle(userdetails.getJobTitle());
        user.setActive(userdetails.isActive());
        user.setEmailVerified(userdetails.isEmailVerified());
        user.setPhoneVerified(userdetails.isPhoneVerified());
        user.setTwoFactorEnabled(userdetails.isTwoFactorEnabled());
        user.setLastLoginAt(userdetails.getLastLoginAt());
        user.setFailedLoginAttempts(userdetails.getFailedLoginAttempts());
        user.setResetPasswordToken(userdetails.getResetPasswordToken());
        user.setLocked(userdetails.isLocked());
        user.setRole(userdetails.getRole());
        user.setPermissions(userdetails.getPermissions());
        user.setPreferredLanguage(userdetails.getPreferredLanguage());
        user.setTimeZone(userdetails.getTimeZone());
        user.setNotificationPreferences(userdetails.getNotificationPreferences());
        user.setCreatedAt(userdetails.getCreatedAt());
        user.setUpdatedAt(userdetails.getUpdatedAt());
        user.setDeletedAt(userdetails.getDeletedAt());
        user.setUserType(userdetails.getUserType());
        user.setSubscriptionPlan(userdetails.getSubscriptionPlan());
        user.setReferralCode(userdetails.getReferralCode());
        user.setInvitedByUserId(userdetails.getInvitedByUserId());


        return userRepository.save(user);
    }
}
