package com.assessment;

import com.assessment.auth.AuthenticationFilter;
import com.assessment.config.ConferenceServiceConfiguration;
import com.assessment.dao.SessionDAO;
import com.assessment.resources.SessionResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConferenceServiceApplication extends Application<ConferenceServiceConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceServiceApplication.class);

    public static void main(String[] args) throws Exception {
        new ConferenceServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ConferenceServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(ConferenceServiceConfiguration configuration, Environment environment) {
        LOGGER.info("Starting Conference Service Application");

        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        Jdbi jdbi = Jdbi.create(dataSourceFactory.build(environment.metrics(), "mysql"));
        jdbi.installPlugin(new SqlObjectPlugin());

        SessionDAO sessionDAO = jdbi.onDemand(SessionDAO.class);

        environment.jersey().register(new AuthenticationFilter(configuration.getApiKey()));
        environment.jersey().register(new SessionResource(sessionDAO));

        environment.jersey().getResourceConfig().getClasses()
                .forEach(clazz -> System.out.println("Registered class: " + clazz.getName()));

        environment.jersey().getResourceConfig().getEndpointsInfo();

    }
}
