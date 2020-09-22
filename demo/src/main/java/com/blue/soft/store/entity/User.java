package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String alter_priv;

	private String alter_routine_priv;

	@Lob
	@Column(name="authentication_string")
	private String authenticationString;

	private String create_priv;

	private String create_routine_priv;

	private String create_tablespace_priv;

	private String create_tmp_table_priv;

	private String create_user_priv;

	private String create_view_priv;

	@Lob
	@Column(name="default_role")
	private String defaultRole;

	private String delete_history_priv;

	private String delete_priv;

	private String drop_priv;

	private String event_priv;

	private String execute_priv;

	private String file_priv;

	private String grant_priv;

	private String host;

	private int id;

	private String index_priv;

	private String insert_priv;

	@Column(name="is_role")
	private String isRole;

	private String lock_tables_priv;

	@Column(name="max_connections")
	private BigInteger maxConnections;

	@Column(name="max_questions")
	private BigInteger maxQuestions;

	@Column(name="max_statement_time")
	private BigDecimal maxStatementTime;

	@Column(name="max_updates")
	private BigInteger maxUpdates;

	@Column(name="max_user_connections")
	private BigInteger maxUserConnections;

	private String name;

	private String password;

	@Lob
	private String password;

	@Column(name="password_expired")
	private String passwordExpired;

	@Lob
	private String plugin;

	private String process_priv;

	private String references_priv;

	private String reload_priv;

	private String repl_client_priv;

	private String repl_slave_priv;

	private String select_priv;

	private String show_db_priv;

	private String show_view_priv;

	private String shutdown_priv;

	@Lob
	@Column(name="ssl_cipher")
	private String sslCipher;

	@Column(name="ssl_type")
	private String sslType;

	private String super_priv;

	private String trigger_priv;

	private String type;

	private String update_priv;

	private String user;

	@Lob
	@Column(name="x509_issuer")
	private String x509Issuer;

	@Lob
	@Column(name="x509_subject")
	private String x509Subject;

	public User() {
	}

	public String getAlter_priv() {
		return this.alter_priv;
	}

	public void setAlter_priv(String alter_priv) {
		this.alter_priv = alter_priv;
	}

	public String getAlter_routine_priv() {
		return this.alter_routine_priv;
	}

	public void setAlter_routine_priv(String alter_routine_priv) {
		this.alter_routine_priv = alter_routine_priv;
	}

	public String getAuthenticationString() {
		return this.authenticationString;
	}

	public void setAuthenticationString(String authenticationString) {
		this.authenticationString = authenticationString;
	}

	public String getCreate_priv() {
		return this.create_priv;
	}

	public void setCreate_priv(String create_priv) {
		this.create_priv = create_priv;
	}

	public String getCreate_routine_priv() {
		return this.create_routine_priv;
	}

	public void setCreate_routine_priv(String create_routine_priv) {
		this.create_routine_priv = create_routine_priv;
	}

	public String getCreate_tablespace_priv() {
		return this.create_tablespace_priv;
	}

	public void setCreate_tablespace_priv(String create_tablespace_priv) {
		this.create_tablespace_priv = create_tablespace_priv;
	}

	public String getCreate_tmp_table_priv() {
		return this.create_tmp_table_priv;
	}

	public void setCreate_tmp_table_priv(String create_tmp_table_priv) {
		this.create_tmp_table_priv = create_tmp_table_priv;
	}

	public String getCreate_user_priv() {
		return this.create_user_priv;
	}

	public void setCreate_user_priv(String create_user_priv) {
		this.create_user_priv = create_user_priv;
	}

	public String getCreate_view_priv() {
		return this.create_view_priv;
	}

	public void setCreate_view_priv(String create_view_priv) {
		this.create_view_priv = create_view_priv;
	}

	public String getDefaultRole() {
		return this.defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	public String getDelete_history_priv() {
		return this.delete_history_priv;
	}

	public void setDelete_history_priv(String delete_history_priv) {
		this.delete_history_priv = delete_history_priv;
	}

	public String getDelete_priv() {
		return this.delete_priv;
	}

	public void setDelete_priv(String delete_priv) {
		this.delete_priv = delete_priv;
	}

	public String getDrop_priv() {
		return this.drop_priv;
	}

	public void setDrop_priv(String drop_priv) {
		this.drop_priv = drop_priv;
	}

	public String getEvent_priv() {
		return this.event_priv;
	}

	public void setEvent_priv(String event_priv) {
		this.event_priv = event_priv;
	}

	public String getExecute_priv() {
		return this.execute_priv;
	}

	public void setExecute_priv(String execute_priv) {
		this.execute_priv = execute_priv;
	}

	public String getFile_priv() {
		return this.file_priv;
	}

	public void setFile_priv(String file_priv) {
		this.file_priv = file_priv;
	}

	public String getGrant_priv() {
		return this.grant_priv;
	}

	public void setGrant_priv(String grant_priv) {
		this.grant_priv = grant_priv;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndex_priv() {
		return this.index_priv;
	}

	public void setIndex_priv(String index_priv) {
		this.index_priv = index_priv;
	}

	public String getInsert_priv() {
		return this.insert_priv;
	}

	public void setInsert_priv(String insert_priv) {
		this.insert_priv = insert_priv;
	}

	public String getIsRole() {
		return this.isRole;
	}

	public void setIsRole(String isRole) {
		this.isRole = isRole;
	}

	public String getLock_tables_priv() {
		return this.lock_tables_priv;
	}

	public void setLock_tables_priv(String lock_tables_priv) {
		this.lock_tables_priv = lock_tables_priv;
	}

	public BigInteger getMaxConnections() {
		return this.maxConnections;
	}

	public void setMaxConnections(BigInteger maxConnections) {
		this.maxConnections = maxConnections;
	}

	public BigInteger getMaxQuestions() {
		return this.maxQuestions;
	}

	public void setMaxQuestions(BigInteger maxQuestions) {
		this.maxQuestions = maxQuestions;
	}

	public BigDecimal getMaxStatementTime() {
		return this.maxStatementTime;
	}

	public void setMaxStatementTime(BigDecimal maxStatementTime) {
		this.maxStatementTime = maxStatementTime;
	}

	public BigInteger getMaxUpdates() {
		return this.maxUpdates;
	}

	public void setMaxUpdates(BigInteger maxUpdates) {
		this.maxUpdates = maxUpdates;
	}

	public BigInteger getMaxUserConnections() {
		return this.maxUserConnections;
	}

	public void setMaxUserConnections(BigInteger maxUserConnections) {
		this.maxUserConnections = maxUserConnections;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordExpired() {
		return this.passwordExpired;
	}

	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public String getPlugin() {
		return this.plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public String getProcess_priv() {
		return this.process_priv;
	}

	public void setProcess_priv(String process_priv) {
		this.process_priv = process_priv;
	}

	public String getReferences_priv() {
		return this.references_priv;
	}

	public void setReferences_priv(String references_priv) {
		this.references_priv = references_priv;
	}

	public String getReload_priv() {
		return this.reload_priv;
	}

	public void setReload_priv(String reload_priv) {
		this.reload_priv = reload_priv;
	}

	public String getRepl_client_priv() {
		return this.repl_client_priv;
	}

	public void setRepl_client_priv(String repl_client_priv) {
		this.repl_client_priv = repl_client_priv;
	}

	public String getRepl_slave_priv() {
		return this.repl_slave_priv;
	}

	public void setRepl_slave_priv(String repl_slave_priv) {
		this.repl_slave_priv = repl_slave_priv;
	}

	public String getSelect_priv() {
		return this.select_priv;
	}

	public void setSelect_priv(String select_priv) {
		this.select_priv = select_priv;
	}

	public String getShow_db_priv() {
		return this.show_db_priv;
	}

	public void setShow_db_priv(String show_db_priv) {
		this.show_db_priv = show_db_priv;
	}

	public String getShow_view_priv() {
		return this.show_view_priv;
	}

	public void setShow_view_priv(String show_view_priv) {
		this.show_view_priv = show_view_priv;
	}

	public String getShutdown_priv() {
		return this.shutdown_priv;
	}

	public void setShutdown_priv(String shutdown_priv) {
		this.shutdown_priv = shutdown_priv;
	}

	public String getSslCipher() {
		return this.sslCipher;
	}

	public void setSslCipher(String sslCipher) {
		this.sslCipher = sslCipher;
	}

	public String getSslType() {
		return this.sslType;
	}

	public void setSslType(String sslType) {
		this.sslType = sslType;
	}

	public String getSuper_priv() {
		return this.super_priv;
	}

	public void setSuper_priv(String super_priv) {
		this.super_priv = super_priv;
	}

	public String getTrigger_priv() {
		return this.trigger_priv;
	}

	public void setTrigger_priv(String trigger_priv) {
		this.trigger_priv = trigger_priv;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdate_priv() {
		return this.update_priv;
	}

	public void setUpdate_priv(String update_priv) {
		this.update_priv = update_priv;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getX509Issuer() {
		return this.x509Issuer;
	}

	public void setX509Issuer(String x509Issuer) {
		this.x509Issuer = x509Issuer;
	}

	public String getX509Subject() {
		return this.x509Subject;
	}

	public void setX509Subject(String x509Subject) {
		this.x509Subject = x509Subject;
	}

}