package domain;

import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.ArrayList;
import java.util.List;


public class Review {

    private String title;
    private String description;
    private String rate;
    private String name;

    public static List<Review> transformFromDataTable(DataTable dataTable) {
        return new Review.ReviewTransformer().transform(dataTable);
    }

    private static class ReviewTransformer implements TableTransformer<List<Review>> {
        @Override
        public List<Review> transform(DataTable table) {
            List<Review> review = new ArrayList<>();

            table.cells()
                    .stream()
                    .skip(1)        // Skip header row
                    .map(fields -> new Review(
                            fields.get(0),
                            fields.get(1),
                            fields.get(2),
                            fields.get(3)))
                    .forEach(review::add);

            return review;
        }
    }

    public Review(String title, String description, String rate, String name) {
        this.title = title;
        this.description = description;
        this.rate = rate;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
