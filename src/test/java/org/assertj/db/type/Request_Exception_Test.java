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
package org.assertj.db.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnection;
import org.assertj.db.common.DefaultDataSource;
import org.assertj.db.common.DefaultPreparedStatement;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

/**
 * Test on loading of the data for a request and exception during the different steps.
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_Exception_Test extends AbstractTest {

  /**
   * This method should fail because the connection throw an exception when getting an object.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_getting_an_object() {
    DataSource ds = new DefaultDataSource();
    Request request = new Request(ds, "select * from movi where id = ?", 1);
    request.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when executing a query.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_executing_a_query() {
    DataSource ds = new DefaultDataSource() {
      @Override
      public Connection getConnection() throws SQLException {
        return new DefaultConnection() {

          @Override
          public PreparedStatement prepareStatement(String sql) throws SQLException {
            return new DefaultPreparedStatement() {

              @Override
              public ResultSet executeQuery() throws SQLException {
                throw new SQLException();
              }
            };
          }
        };
      }
    };
    Request request = new Request(ds, "select * from movi where id = ?", 1);
    request.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when creating a statement.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_creating_a_statement() {
    DataSource ds = new DefaultDataSource() {
      @Override
      public Connection getConnection() throws SQLException {
        return new DefaultConnection() {

          @Override
          public PreparedStatement prepareStatement(String sql) throws SQLException {
            throw new SQLException();
          }
        };
      }
    };
    Request request = new Request(ds, "select * from movi where id = ?", 1);
    request.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when getting a connection.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_getting_a_connection() {
    DataSource ds = new DefaultDataSource() {
      @Override
      public Connection getConnection() throws SQLException {
        throw new SQLException();
      }
    };
    Request request = new Request(ds, "select * from movi where id = ?", 1);
    request.getColumnsNameList();
  }
}
