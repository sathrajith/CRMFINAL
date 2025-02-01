package com.crm.CRM.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "First name is required")
        private String firstName;

        @NotBlank(message = "Last name is required")
        private String lastName;

        @NotBlank
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank
        private String password;

        @Column(name = "profile_image_url")
        private String profileImageUrl;

        @Past(message = "Date of birth must be in the past")
        @Column(name = "date_of_birth")
        private LocalDateTime dateOfBirth;

        private String gender;

        private String address;
        private String city;
        private String state;
        private String country;

        @Pattern(regexp = "\\d{5}(-\\d{4})?", message = "Invalid zip code")
        @Column(name = "zip_code")
        private String zipCode;

        @Column(name = "company_name")
        private String companyName;

        @Column(name = "job_title")
        private String jobTitle;

        @Column(name = "is_active")
        private boolean isActive;

        @Column(name = "is_email_verified")
        private boolean isEmailVerified;

        @Column(name = "is_phone_verified")
        private boolean isPhoneVerified;

        @Column(name = "is_two_factor_enabled")
        private boolean isTwoFactorEnabled;

        @Column(name = "last_login_at")
        private LocalDateTime lastLoginAt;

        @Column(name = "failed_login_attempts")
        private int failedLoginAttempts;

        @Column(name = "reset_password_token")
        private String resetPasswordToken;

        @Column(name = "is_locked")
        private boolean isLocked;

        @Enumerated(EnumType.STRING)
        private Role role;

        @ElementCollection
        @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
        @Column(name = "permission")
        private List<String> permissions;

        @Column(name = "preferred_language")
        private String preferredLanguage;

        @Column(name = "time_zone")
        private String timeZone;

        @Column(name = "notification_preferences")
        private String notificationPreferences;

        @CreationTimestamp
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @Column(name = "user_type")
        private String userType;

        @Column(name = "subscription_plan")
        private String subscriptionPlan;

        @Column(name = "referral_code")
        private String referralCode;

        @Column(name = "invited_by_user_id")
        private Long invitedByUserId;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }

        public LocalDateTime getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public boolean isEmailVerified() {
            return isEmailVerified;
        }

        public void setEmailVerified(boolean emailVerified) {
            isEmailVerified = emailVerified;
        }

        public boolean isPhoneVerified() {
            return isPhoneVerified;
        }

        public void setPhoneVerified(boolean phoneVerified) {
            isPhoneVerified = phoneVerified;
        }

        public boolean isTwoFactorEnabled() {
            return isTwoFactorEnabled;
        }

        public void setTwoFactorEnabled(boolean twoFactorEnabled) {
            isTwoFactorEnabled = twoFactorEnabled;
        }

        public LocalDateTime getLastLoginAt() {
            return lastLoginAt;
        }

        public void setLastLoginAt(LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
        }

        public int getFailedLoginAttempts() {
            return failedLoginAttempts;
        }

        public void setFailedLoginAttempts(int failedLoginAttempts) {
            this.failedLoginAttempts = failedLoginAttempts;
        }

        public String getResetPasswordToken() {
            return resetPasswordToken;
        }

        public void setResetPasswordToken(String resetPasswordToken) {
            this.resetPasswordToken = resetPasswordToken;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        public String getPreferredLanguage() {
            return preferredLanguage;
        }

        public void setPreferredLanguage(String preferredLanguage) {
            this.preferredLanguage = preferredLanguage;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        public String getNotificationPreferences() {
            return notificationPreferences;
        }

        public void setNotificationPreferences(String notificationPreferences) {
            this.notificationPreferences = notificationPreferences;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public LocalDateTime getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getSubscriptionPlan() {
            return subscriptionPlan;
        }

        public void setSubscriptionPlan(String subscriptionPlan) {
            this.subscriptionPlan = subscriptionPlan;
        }

        public String getReferralCode() {
            return referralCode;
        }

        public void setReferralCode(String referralCode) {
            this.referralCode = referralCode;
        }

        public Long getInvitedByUserId() {
            return invitedByUserId;
        }

        public void setInvitedByUserId(Long invitedByUserId) {
            this.invitedByUserId = invitedByUserId;
        }
    }