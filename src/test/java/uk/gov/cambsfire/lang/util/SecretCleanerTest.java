package uk.gov.cambsfire.lang.util;

/*-
 * #%L
 * Java 8+ common language code
 * %%
 * Copyright (C) 2016 - 2017 Cambridgeshire Fire and Rescue Service
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the Cambridgeshire Fire and Rescue Service nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SecretCleanerTest {
    @Test
    public void removeSecretsRemovesDataValuesLinkedToPasswords() {
        assertThat(SecretCleaner.removeSecrets("'abc': 123, 'password': bob"))
                .isEqualTo("'abc': 123, 'password': \"*****\"");
        assertThat(SecretCleaner.removeSecrets("{'abc': 123, 'password': 'bob'}"))
                .isEqualTo("{'abc': 123, 'password': \"*****\"}");
        assertThat(SecretCleaner.removeSecrets("{'abc': { 'password': 'bob' }}"))
                .isEqualTo("{'abc': { 'password': \"*****\"}}");
        assertThat(SecretCleaner.removeSecrets("[abc: 123, password: dsgSERg345&%]"))
                .isEqualTo("[abc: 123, password: \"*****\"]");
        assertThat(SecretCleaner.removeSecrets("[abc: \"123\", password: \"dsgSERg345&%\"]"))
                .isEqualTo("[abc: \"123\", password: \"*****\"]");

    }

    @Test
    public void removeSecretsRemovesDataValuesLinkedToOtherPasswords() {
        assertThat(SecretCleaner.removeSecrets("'abc': 123, 'confirmPassword': 'bob'"))
                .isEqualTo("'abc': 123, 'confirmPassword': \"*****\"");
        assertThat(SecretCleaner.removeSecrets("'abc': 123, 'passwordTemp': 'bob'"))
                .isEqualTo("'abc': 123, 'passwordTemp': \"*****\"");
    }

}
