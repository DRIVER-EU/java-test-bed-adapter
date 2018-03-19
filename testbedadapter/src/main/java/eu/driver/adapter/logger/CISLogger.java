package eu.driver.adapter.logger;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventConstants;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.LogProducer;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.system.Level;
import eu.driver.model.system.Log;

public final class CISLogger extends MarkerIgnoringBase {

	private static final long serialVersionUID = 4267880126821228370L;
	private final Logger logger;
	private final String clientId;
	private final ITimestampProvider timestampProvider;

	/**
	 * Kafka Log Producer that is shared by all Logger instances
	 */
	private static LogProducer producer;
	
	public static Logger logger(Class<?> clazz) {
		return new CISLogger(clazz);
	}

	public CISLogger(Class<?> clazz) {
		logger = LoggerFactory.getLogger(clazz);
		clientId = ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
		name = clazz.getName();
		timestampProvider = new ISO8601TimestampProvider();
	}
	
	public void setLogProducer(LogProducer logProducer) {
		producer = logProducer;
	}

	private Log createLog(Level level, String message) {
		Log log = new Log();
		log.setId(clientId);
		log.setDateTimeSent(new Date().getTime());
		log.setLevel(level);
		log.setLog(message);
		return log;
	}

	private void sendLog(Level level, String message, Throwable thr) {
		Log log = createLog(level, message);
		if (producer != null) {
			producer.send(log);
		}
	}

	/**
	 * For formatted messages, first substitute arguments and then log.
	 *
	 * @param level
	 * @param format
	 * @param arg1
	 * @param arg2
	 */
	private void formatAndLog(Level level, String format, Object arg1, Object arg2) {
		FormattingTuple tp = MessageFormatter.format(format, arg1, arg2);
		sendLog(level, tp.getMessage(), tp.getThrowable());
	}

	/**
	 * For formatted messages, first substitute arguments and then log.
	 *
	 * @param level
	 * @param format
	 * @param arguments
	 *            a list of 3 ore more arguments
	 */
	private void formatAndLog(Level level, String format, Object... arguments) {
		FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);
		sendLog(level, tp.getMessage(), tp.getThrowable());
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}
	
	@Override
	public void trace(String message) {
		if (isTraceEnabled()) {
			logger.trace(message);
		}
	}

	@Override
	public void trace(String message, Object arg) {
		if (isTraceEnabled()) {
			logger.trace(message, arg);
		}
	}

	@Override
	public void trace(String message, Object arg1, Object arg2) {
		if (isTraceEnabled()) {
			logger.trace(message, arg1, arg2);
		}
	}

	@Override
	public void trace(String message, Object... args) {
		if (isTraceEnabled()) {
			logger.trace(message, args);
		}
	}

	@Override
	public void trace(String message, Throwable t) {
		if (isTraceEnabled()) {
			logger.trace(message, t);
		}
	}


	@Override
	public void debug(String message) {
		if (isDebugEnabled()) {
			logger.debug(message);
			sendLog(Level.DEBUG, message, null);
		}
	}

	@Override
	public void debug(String message, Object arg) {
		if (isDebugEnabled()) {
			logger.debug(message, arg);
			formatAndLog(Level.DEBUG, message, arg, null);
		}
	}

	@Override
	public void debug(String message, Object arg1, Object arg2) {
		if (isDebugEnabled()) {
			logger.debug(message, arg1, arg2);
			formatAndLog(Level.DEBUG, message, arg1, arg2);
		}
	}

	@Override
	public void debug(String message, Object... args) {
		if (isDebugEnabled()) {
			logger.debug(message, args);
			formatAndLog(Level.DEBUG, message, args);
		}
	}

	@Override
	public void debug(String message, Throwable t) {
		if (isDebugEnabled()) {
			logger.debug(message, t);
			sendLog(Level.DEBUG, message, t);
		}
	}

	@Override
	public void warn(String message) {
		if (isWarnEnabled()) {
			logger.warn(message);
			sendLog(Level.WARN, message, null);
		}
	}

	@Override
	public void warn(String message, Object arg) {
		if (isWarnEnabled()) {
			logger.warn(message, arg);
			formatAndLog(Level.WARN, message, arg, null);
		}
	}

	@Override
	public void warn(String message, Object arg1, Object arg2) {
		if (isWarnEnabled()) {
			logger.warn(message, arg1, arg2);
			formatAndLog(Level.WARN, message, arg1, arg2);
		}
	}

	@Override
	public void warn(String message, Object... args) {
		if (isWarnEnabled()) {
			logger.warn(message, args);
			formatAndLog(Level.WARN, message, args);
		}
	}

	@Override
	public void warn(String message, Throwable t) {
		if (isWarnEnabled()) {
			logger.warn(message, t);
			sendLog(Level.WARN, message, t);
		}
	}

	@Override
	public void error(String message) {
		if (isErrorEnabled()) {
			logger.error(message);
			sendLog(Level.ERROR, message, null);
		}
	}

	@Override
	public void error(String message, Object arg) {
		if (isErrorEnabled()) {
			logger.error(message, arg);
			formatAndLog(Level.ERROR, message, arg, null);
		}
	}

	@Override
	public void error(String message, Object arg1, Object arg2) {
		if (isErrorEnabled()) {
			logger.error(message, arg1, arg2);
			formatAndLog(Level.ERROR, message, arg1, arg2);
		}
	}

	@Override
	public void error(String message, Object... args) {
		if (isErrorEnabled()) {
			logger.error(message, args);
			formatAndLog(Level.ERROR, message, args);
		}
	}

	@Override
	public void error(String message, Throwable t) {
		if (isErrorEnabled()) {
			logger.error(message, t);
			sendLog(Level.ERROR, message, t);
		}
	}

	@Override
	public void info(String message) {
		if (isInfoEnabled()) {
			logger.info(message);
			sendLog(Level.INFO, message, null);
		}
	}

	@Override
	public void info(String message, Object arg) {
		if (isInfoEnabled()) {
			logger.info(message, arg);
			formatAndLog(Level.INFO, message, arg, null);
		}
	}

	@Override
	public void info(String message, Object arg1, Object arg2) {
		if (isInfoEnabled()) {
			logger.info(message, arg1, arg2);
			formatAndLog(Level.INFO, message, arg1, arg2);
		}
	}

	@Override
	public void info(String message, Object... args) {
		if (isInfoEnabled()) {
			logger.info(message, args);
			formatAndLog(Level.INFO, message, args);
		}
	}

	@Override
	public void info(String message, Throwable t) {
		if (isInfoEnabled()) {
			logger.info(message, t);
			sendLog(Level.INFO, message, t);
		}
	}

}
