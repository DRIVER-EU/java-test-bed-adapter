package eu.driver.adapter.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventConstants;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import eu.driver.adapter.core.LogProducer;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.core.Log;

public final class CISLogger extends MarkerIgnoringBase {

	private static final long serialVersionUID = 4267880126821228370L;
	private final Logger logger;
	private final LogProducer producer;
	private final String clientId;
	private final ITimestampProvider timestampProvider;

	protected static final int LOG_LEVEL_TRACE = EventConstants.TRACE_INT;
	protected static final int LOG_LEVEL_DEBUG = EventConstants.DEBUG_INT;
	protected static final int LOG_LEVEL_INFO = EventConstants.INFO_INT;
	protected static final int LOG_LEVEL_WARN = EventConstants.WARN_INT;
	protected static final int LOG_LEVEL_ERROR = EventConstants.ERROR_INT;

	public static Logger logger(Class<?> clazz) {
		return new CISLogger(clazz);
	}

	public CISLogger(Class<?> clazz) {
		logger = LoggerFactory.getLogger(clazz);
		producer = new LogProducer();
		clientId = ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
		name = clazz.getName();
		timestampProvider = new ISO8601TimestampProvider();
	}

	private Log createLog(String message) {
		Log log = new Log();
		log.setId(clientId);
		log.setLog(message);
		return log;
	}

	private void sendLog(String message) {
		Log log = createLog(message);
		producer.send(log);
	}

	/**
	 * For formatted messages, first substitute arguments and then log.
	 *
	 * @param level
	 * @param format
	 * @param arg1
	 * @param arg2
	 */
	private void formatAndLog(int level, String format, Object arg1, Object arg2) {
		FormattingTuple tp = MessageFormatter.format(format, arg1, arg2);
		log(level, tp.getMessage(), tp.getThrowable());
	}

	/**
	 * For formatted messages, first substitute arguments and then log.
	 *
	 * @param level
	 * @param format
	 * @param arguments
	 *            a list of 3 ore more arguments
	 */
	private void formatAndLog(int level, String format, Object... arguments) {
		FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);
		log(level, tp.getMessage(), tp.getThrowable());
	}

	/**
	 * This is our internal implementation for logging regular (non-parameterized)
	 * log messages.
	 *
	 * @param level
	 *            One of the LOG_LEVEL_XXX constants defining the log level
	 * @param message
	 *            The message itself
	 * @param t
	 *            The exception whose stack trace should be logged
	 */
	private void log(int level, String message, Throwable t) {
		StringBuilder buf = new StringBuilder(32);

		buf.append(timestampProvider.getTimestamp());
		buf.append(' ');

		// Append a readable representation of the log level
		String levelStr = renderLevel(level);
		buf.append(levelStr);
		buf.append(' ');

		// Append the name of the log instance if so configured
		buf.append(logger.getName()).append(" - ");

		// Append the message
		buf.append(message);

		// write to Kafka topic
		sendLog(buf.toString());

		// TODO: what to do with Throwable?
	}

	protected String renderLevel(int level) {
		switch (level) {
		case LOG_LEVEL_TRACE:
			return "TRACE";
		case LOG_LEVEL_DEBUG:
			return "DEBUG";
		case LOG_LEVEL_INFO:
			return "INFO";
		case LOG_LEVEL_WARN:
			return "WARN";
		case LOG_LEVEL_ERROR:
			return "ERROR";
		}
		throw new IllegalStateException("Unrecognized level [" + level + "]");
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
			log(LOG_LEVEL_TRACE, message, null);
		}
	}

	@Override
	public void trace(String message, Object arg) {
		if (isTraceEnabled()) {
			logger.trace(message, arg);
			formatAndLog(LOG_LEVEL_TRACE, message, arg, null);
		}
	}

	@Override
	public void trace(String message, Object arg1, Object arg2) {
		if (isTraceEnabled()) {
			logger.trace(message, arg1, arg2);
			formatAndLog(LOG_LEVEL_TRACE, message, arg1, arg2);
		}
	}

	@Override
	public void trace(String message, Object... args) {
		if (isTraceEnabled()) {
			logger.trace(message, args);
			formatAndLog(LOG_LEVEL_TRACE, message, args);
		}
	}

	@Override
	public void trace(String msg, Throwable t) {
		if (isTraceEnabled()) {
			logger.trace(msg, t);
			log(LOG_LEVEL_TRACE, msg, t);
		}
	}

	@Override
	public void debug(String message) {
		if (isDebugEnabled()) {
			logger.debug(message);
			log(LOG_LEVEL_DEBUG, message, null);
		}
	}

	@Override
	public void debug(String message, Object arg) {
		if (isDebugEnabled()) {
			logger.debug(message, arg);
			formatAndLog(LOG_LEVEL_DEBUG, message, arg, null);
		}
	}

	@Override
	public void debug(String message, Object arg1, Object arg2) {
		if (isDebugEnabled()) {
			logger.debug(message, arg1, arg2);
			formatAndLog(LOG_LEVEL_DEBUG, message, arg1, arg2);
		}
	}

	@Override
	public void debug(String message, Object... args) {
		if (isDebugEnabled()) {
			logger.debug(message, args);
			formatAndLog(LOG_LEVEL_DEBUG, message, args);
		}
	}

	@Override
	public void debug(String message, Throwable t) {
		if (isDebugEnabled()) {
			logger.debug(message, t);
			log(LOG_LEVEL_DEBUG, message, t);
		}
	}

	@Override
	public void warn(String message) {
		if (isWarnEnabled()) {
			logger.warn(message);
			log(LOG_LEVEL_WARN, message, null);
		}
	}

	@Override
	public void warn(String message, Object arg) {
		if (isWarnEnabled()) {
			logger.warn(message, arg);
			formatAndLog(LOG_LEVEL_WARN, message, arg, null);
		}
	}

	@Override
	public void warn(String message, Object arg1, Object arg2) {
		if (isWarnEnabled()) {
			logger.warn(message, arg1, arg2);
			formatAndLog(LOG_LEVEL_WARN, message, arg1, arg2);
		}
	}

	@Override
	public void warn(String message, Object... args) {
		if (isWarnEnabled()) {
			logger.warn(message, args);
			formatAndLog(LOG_LEVEL_WARN, message, args);
		}
	}

	@Override
	public void warn(String message, Throwable t) {
		if (isWarnEnabled()) {
			logger.warn(message, t);
			log(LOG_LEVEL_WARN, message, t);
		}
	}

	@Override
	public void error(String message) {
		if (isErrorEnabled()) {
			logger.error(message);
			log(LOG_LEVEL_ERROR, message, null);
		}
	}

	@Override
	public void error(String message, Object arg) {
		if (isErrorEnabled()) {
			logger.error(message, arg);
			formatAndLog(LOG_LEVEL_ERROR, message, arg, null);
		}
	}

	@Override
	public void error(String message, Object arg1, Object arg2) {
		if (isErrorEnabled()) {
			logger.error(message, arg1, arg2);
			formatAndLog(LOG_LEVEL_ERROR, message, arg1, arg2);
		}
	}

	@Override
	public void error(String message, Object... args) {
		if (isErrorEnabled()) {
			logger.error(message, args);
			formatAndLog(LOG_LEVEL_ERROR, message, args);
		}
	}

	@Override
	public void error(String message, Throwable t) {
		if (isErrorEnabled()) {
			logger.error(message, t);
			log(LOG_LEVEL_ERROR, message, t);
		}
	}

	@Override
	public void info(String message) {
		if (isInfoEnabled()) {
			logger.info(message);
			log(LOG_LEVEL_INFO, message, null);
		}
	}

	@Override
	public void info(String message, Object arg) {
		if (isInfoEnabled()) {
			logger.info(message, arg);
			formatAndLog(LOG_LEVEL_INFO, message, arg, null);
		}
	}

	@Override
	public void info(String message, Object arg1, Object arg2) {
		if (isInfoEnabled()) {
			logger.info(message, arg1, arg2);
			formatAndLog(LOG_LEVEL_INFO, message, arg1, arg2);
		}
	}

	@Override
	public void info(String message, Object... args) {
		if (isInfoEnabled()) {
			logger.info(message, args);
			formatAndLog(LOG_LEVEL_INFO, message, args);
		}
	}

	@Override
	public void info(String message, Throwable t) {
		if (isInfoEnabled()) {
			logger.info(message, t);
			log(LOG_LEVEL_INFO, message, t);
		}
	}

}
