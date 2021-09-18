package domain;

import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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
}
