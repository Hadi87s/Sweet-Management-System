package Sweet.System;

public class Recipe {

    private String option;
    private String title;
    private String description;
    private String Calories,Fat,Sugar,Protein,FoodAllergies;

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getFat() {
        return Fat;
    }

    public void setFat(String fat) {
        Fat = fat;
    }

    public String getSugar() {
        return Sugar;
    }

    public void setSugar(String sugar) {
        Sugar = sugar;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public String getFoodAllergies() {
        return FoodAllergies;
    }

    public void setFoodAllergies(String foodAllergies) {
        FoodAllergies = foodAllergies;
    }

    public String getNutrient() {

        return "Nutrient: "+"Calories: "+Calories+" Fat: "+Fat+" Sugar: "+Sugar+" Protein: "+Protein;
    }


    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return
                title + ": " + description + "\n";
    }
}
