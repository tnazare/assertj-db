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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValuesEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, byte[]...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnEquality_HasValuesEqualTo_Bytes_Test {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_have_values_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(new byte[] {0, 1}, new byte[] {2, 3}));
    TableAssert tableAssert2 = AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, new byte[] {0, 1}, new byte[] {2, 3});
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(new byte[] {0, 1}, new byte[] {2, 3}, null));
    try {
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, new byte[] {0, 1}, new byte[] {1, 3}, null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1 to be equal to the expected value but was not equal");
    }
  }

  /**
   * This method should fail because one of the values is not bytes.
   */
  @Test
  public void should_fail_because_one_value_is_not_bytes() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList("other", new byte[] {2, 3}));
    try {
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, new byte[] {0, 1}, new byte[] {2, 3});
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <\"other\">\n"
                                                      + "to be of type\n"
                                                      + "  <[BYTES, NOT_IDENTIFIED]>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(new byte[] {0, 1}, new byte[] {2, 3}));
    try {
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, new byte[] {0, 1}, new byte[] {2, 3}, new byte[] {4, 5});
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting size (number of rows) to be equal to :\n"
                                                      + "   <3>\n"
                                                      + "but was:\n"
                                                      + "   <2>");
    }
  }
}
