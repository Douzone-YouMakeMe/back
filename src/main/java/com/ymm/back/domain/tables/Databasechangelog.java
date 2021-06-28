/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.domain.tables;


import com.ymm.back.domain.Hwant;
import com.ymm.back.domain.tables.records.DatabasechangelogRecord;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Databasechangelog extends TableImpl<DatabasechangelogRecord> {

    private static final long serialVersionUID = -85952693;

    /**
     * The reference instance of <code>hwant.DATABASECHANGELOG</code>
     */
    public static final Databasechangelog DATABASECHANGELOG = new Databasechangelog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangelogRecord> getRecordType() {
        return DatabasechangelogRecord.class;
    }

    /**
     * The column <code>hwant.DATABASECHANGELOG.ID</code>.
     */
    public final TableField<DatabasechangelogRecord, String> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.AUTHOR</code>.
     */
    public final TableField<DatabasechangelogRecord, String> AUTHOR = createField(DSL.name("AUTHOR"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.FILENAME</code>.
     */
    public final TableField<DatabasechangelogRecord, String> FILENAME = createField(DSL.name("FILENAME"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    public final TableField<DatabasechangelogRecord, LocalDateTime> DATEEXECUTED = createField(DSL.name("DATEEXECUTED"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    public final TableField<DatabasechangelogRecord, Integer> ORDEREXECUTED = createField(DSL.name("ORDEREXECUTED"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.EXECTYPE</code>.
     */
    public final TableField<DatabasechangelogRecord, String> EXECTYPE = createField(DSL.name("EXECTYPE"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.MD5SUM</code>.
     */
    public final TableField<DatabasechangelogRecord, String> MD5SUM = createField(DSL.name("MD5SUM"), org.jooq.impl.SQLDataType.VARCHAR(35), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.COMMENTS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> COMMENTS = createField(DSL.name("COMMENTS"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.TAG</code>.
     */
    public final TableField<DatabasechangelogRecord, String> TAG = createField(DSL.name("TAG"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LIQUIBASE = createField(DSL.name("LIQUIBASE"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.CONTEXTS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> CONTEXTS = createField(DSL.name("CONTEXTS"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.LABELS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LABELS = createField(DSL.name("LABELS"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>hwant.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DEPLOYMENT_ID = createField(DSL.name("DEPLOYMENT_ID"), org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * Create a <code>hwant.DATABASECHANGELOG</code> table reference
     */
    public Databasechangelog() {
        this(DSL.name("DATABASECHANGELOG"), null);
    }

    /**
     * Create an aliased <code>hwant.DATABASECHANGELOG</code> table reference
     */
    public Databasechangelog(String alias) {
        this(DSL.name(alias), DATABASECHANGELOG);
    }

    /**
     * Create an aliased <code>hwant.DATABASECHANGELOG</code> table reference
     */
    public Databasechangelog(Name alias) {
        this(alias, DATABASECHANGELOG);
    }

    private Databasechangelog(Name alias, Table<DatabasechangelogRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangelog(Name alias, Table<DatabasechangelogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Databasechangelog(Table<O> child, ForeignKey<O, DatabasechangelogRecord> key) {
        super(child, key, DATABASECHANGELOG);
    }

    @Override
    public Schema getSchema() {
        return Hwant.HWANT;
    }

    @Override
    public Databasechangelog as(String alias) {
        return new Databasechangelog(DSL.name(alias), this);
    }

    @Override
    public Databasechangelog as(Name alias) {
        return new Databasechangelog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangelog rename(String name) {
        return new Databasechangelog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangelog rename(Name name) {
        return new Databasechangelog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<String, String, String, LocalDateTime, Integer, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}
