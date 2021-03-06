package eu.driver.adapter.constants;

public class TopicConstants {
	public TopicConstants() { }
	
	public static final String HEARTBEAT_TOPIC = "system_heartbeat";
	public static final String ADMIN_HEARTBEAT_TOPIC = "system_admin_heartbeat";
	public static final String LOGGING_TOPIC = "system_logging";
	public static final String EVALUATION_LOGGING_TOPIC = "system_evaluation_logging";
	public static final String TOPIC_INVITE_TOPIC = "system_topic_access_invite";
	public static final String TOPIC_REMOVE_TOPIC = "system_topic_remove";
	public static final String TOPIC_CREATE_REQUEST_TOPIC = "system_topic_create_request";
	public static final String TOPIC_REMOVE_REQUEST_TOPIC = "system_topic_remove_request";
	public static final String TIMING_TOPIC = "simulation_time_mgmt";
	public static final String TIMING_CONTROL_TOPIC = "simulation_time_control";
	public static final String CONFIG_TOPIC = "system_config";
	public static final String TRIAL_STATE_CHANGE_TOPIC = "system_request_change_of_trial_stage";
	public static final String OST_ANSWER_TOPIC = "system_observer_tool_answer";
	
	public static final String PHASE_MESSAGE_TOPIC = "system_tm_phase_message";
	public static final String ROLE_PLAYER_TOPIC = "system_tm_role_player";
	public static final String SESSION_MGMT_TOPIC = "system_tm_session_mgmt";
	
	public static final String LARGE_DATA_UPDTAE = "large_data_update";
	public static final String MAP_LAYER_UPDTAE = "map_layer_update";
	
	public static final String STANDARD_TOPIC_CAP = "standard_cap";
	public static final String STANDARD_TOPIC_MLP = "standard_mlp";
	public static final String STANDARD_TOPIC_EMSI = "standard_emsi";
	public static final String STANDARD_TOPIC_GEOJSON = "standard_geojson";
	public static final String STANDARD_TOPIC_GEOJSON_STATION = "standard_geojson_sim_station";
	public static final String STANDARD_TOPIC_GEOJSON_UNIT = "standard_geojson_sim_unit";
	public static final String STANDARD_TOPIC_GEOJSON_ITEM = "standard_geojson_sim_item";
	public static final String STANDARD_TOPIC_GEOJSON_UNITGROUP = "standard_geojson_sim_unitgroup";
	public static final String STANDARD_TOPIC_GEOJSON_XVR = "standard_geojson_xvr";
}
