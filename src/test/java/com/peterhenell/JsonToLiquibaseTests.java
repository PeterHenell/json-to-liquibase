package com.peterhenell;

//import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

import liquibase.change.Change;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;

public class JsonToLiquibaseTests {

	@Test
	public void test() {
		JsonToLiquibase jtl = new JsonToLiquibase();
		
//		DatabaseChangeLog changelog = jtl.toLiquibase("{\"JSON\": \"Hello, World\"}");
//		
//		Database database = DatabaseFactory.getInstance().getDatabase(shortName)
//		
//		
//		assertThat(changelog.getChangeSets().size(), is(1));
//		}
//		System.out.println();
		jtl.GenerateChangeLog();
	}
}
