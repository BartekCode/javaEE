package readstack.domain.api;

public class CategoryName {
    //obiekt Dto
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryName(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    //Na stronie głównej nie są nam potrzebne opisy kategorii, dlatego tworzę obiekt Dto zawierający jedynie id oraz nazwę kategorii.
}
