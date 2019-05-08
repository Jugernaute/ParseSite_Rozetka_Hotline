package compare.site;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import javax.sql.DataSource;

import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SiteApplication {

    @Autowired
    DataSource hikariDataSource;


//    @Value("${DATABASE_URL}")
//    private String dbUrl;


    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//        75929ba5
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        System.out.println(username);
        System.out.println(password);
        System.out.println(dbUrl);
        System.out.println(dbUri.getHost());
        System.out.println(dbUri.getPath());

        return basicDataSource;
    }


    @RequestMapping("/hello")
    String hello(Map<String, Object> model) throws URISyntaxException, SQLException {
        RelativisticModel.select();
        dataSource();
        Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
        model.put("science", "E=mc^2: 12 GeV = " + m.toString());
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);

    }

//    @Bean
//    public HikariDataSource dataSource() throws SQLException {
//        if (dbUrl == null || dbUrl.isEmpty()) {
//            return new HikariDataSource();
//        } else {
//            HikariConfig config = new HikariConfig();
//            config.setJdbcUrl(dbUrl);
//            return new HikariDataSource(config);
//        }
//    }

//    @RequestMapping("/db")
//    String db(Map<String, Object> model) {
//        DataSource dataSource = hikariDataSource();
//        try (Connection connection = dataSource.getConnection()) {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("DROP TABLE ticks");
//            stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
//            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//            ArrayList<String> output = new ArrayList<String>();
//            while (rs.next()) {
//                output.add("Read from DB: " + rs.getTimestamp("tick"));
//            }
//
//            model.put("records", output);
//            return "hello";
//        } catch (Exception e) {
//            System.out.println(e);
//            model.put("message", e.getMessage());
//            return "loginError";
//        }
//    }

//    DataSource hikariDataSource(){
//        if (dbUrl == null || dbUrl.isEmpty()) {
//            return new HikariDataSource();
//        } else {
//            HikariConfig config = new HikariConfig();
//            config.setJdbcUrl(dbUrl);
//            return new HikariDataSource(config);
//        }
//    }


}
