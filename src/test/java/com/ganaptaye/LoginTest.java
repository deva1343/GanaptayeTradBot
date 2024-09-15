package com.ganaptaye;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ganaptayeTradBot.TradingBotApplication; // Update with the correct package
import com.ganaptayeTradBot.auth.KotakNeoAuthService;

import java.io.IOException;
import java.util.Scanner;

@SpringBootTest(classes = TradingBotApplication.class) // Ensure this points to your main application class
public class LoginTest {

    @Autowired
    private KotakNeoAuthService kotakNeoAuthService;

    @Test
    public void testGenerateOtpAndLogin() {
        // Verify if the service bean is correctly injected
        Assert.assertNotNull(kotakNeoAuthService, "KotakNeoAuthService should not be null");

        try {
            // Generate OTP
            String otp = kotakNeoAuthService.generateOtp();
            Assert.assertNotNull(otp, "OTP should not be null");

            // Print OTP for manual entry
            System.out.println("Generated OTP: " + otp);

            // Prompt user to enter the OTP received on their mobile
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter the OTP received on your mobile: ");
                String enteredOtp = scanner.nextLine();

                // Use the OTP to get session token
                String sessionToken = kotakNeoAuthService.getSessionToken(enteredOtp);
                Assert.assertNotNull(sessionToken, "Session token should not be null");

                // Optionally, check token validity here
                String tokenDetails = kotakNeoAuthService.viewToken();
                Assert.assertTrue(tokenDetails.contains("expectedContent"), "Token details should contain expected content");
            }

        } catch (IOException e) {
            Assert.fail("Exception occurred during OTP generation or session token retrieval", e);
        }
    }
}
