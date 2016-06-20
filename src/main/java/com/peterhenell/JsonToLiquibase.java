package com.peterhenell;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import liquibase.CatalogAndSchema;
import liquibase.change.Change;
import liquibase.change.core.CreateTableChange;
import liquibase.change.core.supplier.CreateTableChangeSupplier;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.ObjectQuotingStrategy;
import liquibase.diff.DiffResult;
import liquibase.diff.compare.CompareControl;
import liquibase.diff.compare.core.TableComparator;
import liquibase.diff.output.DiffOutputControl;
import liquibase.diff.output.changelog.DiffToChangeLog;
import liquibase.exception.DatabaseException;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Table;
import liquibase.util.StringUtils;

public class JsonToLiquibase {

	public DatabaseChangeLog toLiquibase(String string) {

		DatabaseChangeLog dbChangeLog = new DatabaseChangeLog();
		ObjectQuotingStrategy qs = ObjectQuotingStrategy.QUOTE_ALL_OBJECTS;

		ChangeSet cs = new ChangeSet("id", "pehe", false, false, "", "", "", true, qs, dbChangeLog);
		CreateTableChange createTableChange = new CreateTableChange();
		createTableChange.setCatalogName("testDB");
		createTableChange.setTableName("JSON");

		cs.addChange(createTableChange);
		Change change = null;
		cs.addChange(change);

		dbChangeLog.addChangeSet(cs);
		return dbChangeLog;
	}

	public void GenerateChangeLog() {
		DiffResult diffResult = createDiffResult();

		DiffOutputControl diffOutputControl = new DiffOutputControl(false, true, true);
		DiffToChangeLog changeLogWriter = new DiffToChangeLog(diffResult, diffOutputControl);

		changeLogWriter.setChangeSetAuthor("");
		changeLogWriter.setChangeSetContext("");
		changeLogWriter.setChangeSetPath("");
		try {
			changeLogWriter.print(System.out);
		} catch (DatabaseException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private DiffResult createDiffResult() {
		CompareControl compareControl = new CompareControl();
		DatabaseSnapshot comp;
		DatabaseSnapshot refSnap;
		
		DiffResult diffResult = new DiffResult(refSnap, comp, compareControl);
		Table o = new Table("db1", "Schema1", "TableName1");
		diffResult.addMissingObject(o);
		return null;
	}
	
	protected DatabaseSnapshot createTargetSnapshot() throws DatabaseException, InvalidExampleException {
        CatalogAndSchema[] schemas;

            schemas =new CatalogAndSchema[compareControl.getSchemaComparisons().length];
            int i = 0;
            for (CompareControl.SchemaComparison comparison : compareControl.getSchemaComparisons()) {
                schemas[i++] = comparison.getComparisonSchema();
            }
        }
        SnapshotControl snapshotControl = getTargetSnapshotControl();
        if (snapshotControl == null) {
            snapshotControl = new SnapshotControl(targetDatabase, snapshotTypes);
        }
        if (getSnapshotListener() != null) {
            snapshotControl.setSnapshotListener(getSnapshotListener());
        }
        return SnapshotGeneratorFactory.getInstance().createSnapshot(schemas, targetDatabase, snapshotControl);
    }

}
