package com.crm.CRM.controller;


import com.crm.CRM.entity.User;
import com.crm.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 2. Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 3. Get all users with pagination
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam int page, @RequestParam int size) {
        List<User> users = userService.getAllUsers(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 4. Delete a user (permanently)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Soft delete a user
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long id) {
        try {
            userService.softDeleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 6. Reset user's password
    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        try {
            userService.resetPassword(id, newPassword);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 7. Increment failed login attempts and lock account if threshold is exceeded
    @PutMapping("/increment-login-attempts/{id}")
    public ResponseEntity<Void> incrementFailedLoginAttempts(@PathVariable Long id) {
        try {
            userService.incrementFailedLoginAttempts(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 8. Check if the user account is locked
    @GetMapping("/locked/{id}")
    public ResponseEntity<Boolean> checkIfLocked(@PathVariable Long id) {
        try {
            boolean isLocked = userService.checkIfLocked(id);
            return new ResponseEntity<>(isLocked, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 9. Check if the user has a specific role
    @GetMapping("/role/{id}/{role}")
    public ResponseEntity<Boolean> checkRole(@PathVariable Long id, @PathVariable String role) {
        try {
            boolean hasRole = userService.checkRole(id, role);
            return new ResponseEntity<>(hasRole, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 10. Check if the user has a specific permission
    @GetMapping("/permissions/{id}/{permission}")
    public ResponseEntity<Boolean> checkPermissions(@PathVariable Long id, @PathVariable String permission) {
        try {
            boolean hasPermission = userService.checkPermissions(id, permission);
            return new ResponseEntity<>(hasPermission, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 11. Update user's subscription plan
    @PutMapping("/subscription/{id}")
    public ResponseEntity<Void> updateSubscriptionPlan(@PathVariable Long id, @RequestBody String newSubscriptionPlan) {
        try {
            userService.updateSubscriptionPlan(id, newSubscriptionPlan);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 12. Update user's notification preferences
    @PutMapping("/notification-preferences/{id}")
    public ResponseEntity<Void> updateNotificationPreferences(@PathVariable Long id, @RequestBody String newPreferences) {
        try {
            userService.updateNotificationPreferences(id, newPreferences);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 13. Validate if the provided email is valid
    @GetMapping("/validate-email")
    public ResponseEntity<Boolean> isValidEmail(@RequestParam String email) {
        boolean isValid = userService.isValidEmail(email);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    // 14. Update user's details
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userdetails) {
        try {
            User updatedUser = userService.updateUser(id, userdetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

