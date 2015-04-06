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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isDate(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnType_IsDate_Test {

  /**
   * This method tests the {@code isBoolean} assertion method.
   */
  @Test
  public void test_is_date() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"),
                                                            Date.valueOf("2002-07-25")));
    TableAssert tableAssert2 = AssertionsOnColumnType.isDate(tableAssert, info, list, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"),
                                               Date.valueOf("2002-07-25")));
    tableAssert2 = AssertionsOnColumnType.isDate(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(null,
                                               Date.valueOf("2002-07-25")));
    tableAssert2 = AssertionsOnColumnType.isDate(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different type.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList("test", Date.valueOf("2002-07-25")));
      AssertionsOnColumnType.isDate(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <DATE>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }

  /**
   * This method should fail because the value at end point have different type.
   */
  @Test
  public void should_fail_because_value_at_end_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"), "test"));
      AssertionsOnColumnType.isDate(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <DATE>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }
}