package eu.driver.adapter.constants;

public enum AdapterMode {
	DEV_MODE(1),
	SEC_DEV_MODE(2),
	TRIAL_MODE(3);
	
	private final int value;

    private AdapterMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
