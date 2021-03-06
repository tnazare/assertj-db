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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code UUID}s and {@code String}s.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class Values_AreEqual_UUID_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code UUID}s.
   */
  @Test
  public void test_are_equal_for_UUIDs() {
    assertThat(Values.areEqual(UUID.fromString("88838129-291E-40A9-A94C-A15BE36CF7C3"),
                               "88838129-291E-40A9-A94C-A15BE36CF7C3")).isTrue();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(UUID.fromString("88838129-291E-40A9-A94C-A15BE36CF7C3"), "***");
  }

}
