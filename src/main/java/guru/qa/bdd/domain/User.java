package guru.qa.bdd.domain;


import guru.qa.bdd.utils.RandomUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmation;

    public User(String title, String firstname, String lastname, String email, String password, String confirmation) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.confirmation = confirmation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public static List<User> transformFromDataTable(DataTable dataTable) {
        return new UserTransformer().transform(dataTable);
    }

    private static class UserTransformer implements TableTransformer<List<User>> {
        @Override
        public List<User> transform(DataTable table) {
            List<User> users = new ArrayList<>();
            table.cells()
                    .stream()
                    .skip(1)        // Skip header row
                    .map(fields -> new User(
                            fields.get(0),
                            fields.get(1),
                            fields.get(2),
                            fields.get(3) != null && fields.get(3).equals("RANDOM_EMAIL") ? RandomUtils.getRandomEmail() : fields.get(3),
                            fields.get(4),
                            fields.get(5)))
                    .forEach(users::add);

            return users;
        }
    }
}



