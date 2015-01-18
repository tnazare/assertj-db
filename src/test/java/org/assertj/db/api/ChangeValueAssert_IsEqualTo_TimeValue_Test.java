/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Changes;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is equal to a time value.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsEqualTo_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a time.
   * @throws java.text.ParseException
   */
  @Test
  public void test_if_value_is_equal_to_time() throws ParseException {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint().value("var8")
                     .isEqualTo(TimeValue.of(9, 46, 30))
            .change().rowAtEndPoint().value("var8")
                     .isEqualTo(TimeValue.parse("12:29:49"));
  }

  /**
   * This method should fail because the value is not equal to the time value.
   */
  @Test
  public void should_fail_because_value_is_not_equal() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
             + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
      update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
      update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var8")
                       .isEqualTo(TimeValue.of(9, 46, 31));

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 7 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <09:46:30.000000000>\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <09:46:31.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
             + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
      update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
      update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1")
                       .as("var1").isEqualTo(TimeValue.of(9, 46, 30));

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <TIME>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NUMBER>");
    }
  }

}