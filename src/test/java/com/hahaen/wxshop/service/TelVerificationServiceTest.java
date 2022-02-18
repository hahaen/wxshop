package com.hahaen.wxshop.service;

import com.hahaen.wxshop.AutoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TelVerificationServiceTest {
    private static AutoController.TelAndCode VALID_PARAMETER = new AutoController.TelAndCode("13012345678", null);
    private static AutoController.TelAndCode EMPTY_TEL = new AutoController.TelAndCode(null, null);

    @Test
    public void returnTrueIfValid() {
        Assertions.assertTrue(new TelVerificationService().verifyTelParameter(VALID_PARAMETER));
    }

    @Test
    public void returnFalseIfNoTel() {
        Assertions.assertFalse(new TelVerificationService().verifyTelParameter(EMPTY_TEL));
        Assertions.assertFalse(new TelVerificationService().verifyTelParameter(null));
    }
}
