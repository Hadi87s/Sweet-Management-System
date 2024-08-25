package system;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SweetSystem {
    private boolean registeredIn;
    private boolean userValid;
    private String message;
    private boolean recipeAdded;
    private boolean postAdded;
    private boolean productAdded;
    private boolean messageSent;
    private boolean emailNotificationsEnabled;
    private boolean specialRequestMade;
    private String lastEmailNotificationContent;

    private static final Logger LOGGER = Logger.getLogger(SweetSystem.class.getName());

    private static List<User> users = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<StoreOwner> storeOwners = new ArrayList<>();
    private static List<RawSupplier> suppliers = new ArrayList<>();
    private static List<Post> posts = new ArrayList<>();
    private static List<Recipe> recipes = new ArrayList<>();
    private  static final List<Feedback> feedbacks = new ArrayList<>();
    private static final String EMAIL_REGEX = "a";

    // Compile the pattern once and reuse it
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public SweetSystem() throws IOException {
        registeredIn = false;
        userValid = false;
        recipeAdded = false;
        postAdded = false;
        productAdded = false;
        messageSent = false;
        final String usersFile = "Users.txt";
        final String adminsFile = "Admins.txt";
        final String storeOwnersFile = "StoreOwners.txt";
        final String suppliersFile = "Suppliers.txt";
        final String productsFile = "Products.txt";

        loadUsersFromFile(usersFile);
        loadAdminsFromFile(adminsFile);
        loadStoreOwnersFromFile(storeOwnersFile);
        loadSuppliersFromFile(suppliersFile);
        StoreOwner.loadProductsFromFile(productsFile);

        User zahi = new User("User1", "123", "user1@example.com", "Nablus");
        Feedback zahiQudo = new Feedback("Chocolate Cake Was Crazy\n");
        feedbacks.add(zahiQudo);

        Order order = new Order("ChocolateCake", 2, "12345");
        zahi.addOrder(order);
        users.add(zahi);

        Recipe recipe1 = new Recipe("Kunafa", "dough");
        recipe1.setOption( "Popular sweets");
        recipe1.setProtein("51g");
        recipe1.setFat("221g");
        recipe1.setSugar("741g");
        recipe1.setCalories("3105");
        recipe1.setFoodAllergies("Butter");
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe("Chocolate Cake", "aCakeFlavoredWithMeltedChocolate, cocoa powder");
        recipe2.setOption( "Cake");
        recipe2.setProtein("5g");
        recipe2.setFat("22g");
        recipe2.setSugar("74g");
        recipe2.setCalories("305");
        recipe2.setFoodAllergies("dairy");
        recipes.add(recipe2);

    }

    private void loadUsersFromFile(String filename) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(" ");
                if (userData.length == 4) {
                    String username = userData[0];
                    String password = userData[1];
                    String email = userData[2];
                    String city = userData[3];

                    User user = new User(username, password, email, city);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAdminsFromFile(String filename) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminData = line.split(" ");
                if (adminData.length == 2) {
                    String username = adminData[0];
                    String password = adminData[1];

                    Admin admin = new Admin(username, password);
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean addAdminToFile(String filename, Admin admin) {
        boolean isAdded = false;

        // Add the admin to the in-memory list
        admins.add(admin);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // 'true' to append to the file
            // Format the admin data as "username password"
            String adminData = admin.getUsername() + " " +
                    admin.getPassword();
            writer.write(adminData);
            writer.newLine(); // Add a newline after writing the admin data
            isAdded = true;
        } catch (IOException e) {
            e.printStackTrace();
            isAdded = false;
        }

        return isAdded;
    }
    public void loadStoreOwnersFromFile(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] storeOwnerData = line.split(" ");
                if (storeOwnerData.length == 5) {
                    String username = storeOwnerData[0];
                    String password = storeOwnerData[1];
                    String email = storeOwnerData[2];
                    String businessName = storeOwnerData[3];
                    String address = storeOwnerData[4];

                    StoreOwner storeOwner = new StoreOwner(username, password, email,address);
                    storeOwner.setBusinessName(businessName);
                    storeOwners.add(storeOwner);
                } else {
                    LOGGER.log(Level.WARNING, "Invalid data format in file: {0}", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean addStoreOwnerToFile(String fileName, StoreOwner storeOwner) {
        boolean isAdded = false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) { // 'true' to append to the file
            // Format the store owner data as "username password email businessName address"
            String storeOwnerData = storeOwner.getUsername() + " " +
                    storeOwner.getPassword() + " " +
                    storeOwner.getEmail() + " " +
                    storeOwner.getBusinessName() + " " +
                    storeOwner.getAddress();
            storeOwners.add(storeOwner); //adding the new owner to the list as well.
            if (storeOwners.contains(storeOwner)) {
                isAdded = true;
            }
            bw.write(storeOwnerData);
            bw.newLine(); // Add a newline after writing the store owner data
        } catch (IOException e) {
            e.printStackTrace();
            isAdded = false;
        }
        return isAdded;
    }
    public boolean removeStoreOwnerFromList( String username) {
        Iterator<StoreOwner> iterator = storeOwners.iterator();
        boolean isRemoved = false;

        while (iterator.hasNext()) {
            StoreOwner storeOwner = iterator.next();
            if (storeOwner.getUsername().equals(username)) {
                iterator.remove();  // Remove the store owner from the list
                isRemoved = true;
                break;  // Exit the loop after removing the store owner
            }
        }

        return isRemoved;
    }


    public void loadSuppliersFromFile(String filename) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] supplierData = line.split(" ");
                if (supplierData.length == 3) {
                    String username = supplierData[0];
                    String password = supplierData[1];
                    String email = supplierData[2];

                    RawSupplier supplier = new RawSupplier(username, password, email);
                    suppliers.add(supplier);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean addSupplierToFile(String filename, RawSupplier supplier) {
        boolean isAdded = false;

        suppliers.add(supplier);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // 'true' to append to the file
            // Format the supplier data as "username password email"
            String supplierData = supplier.getUsername() + " " +
                    supplier.getPassword() + " " +
                    supplier.getEmail();
            writer.write(supplierData);
            writer.newLine(); // Add a newline after writing the supplier data
            isAdded = true;
        } catch (IOException e) {
            e.printStackTrace();
            isAdded = false;
        }

        return isAdded;
    }

    public void addUserToFile(String filename, User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String userLine = String.format("%s %s %s %s", user.getUsername(), user.getPassword(), user.getEmail(), user.getCity());
            writer.write(userLine);
            writer.newLine(); // Add a newline after the user data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegisteredIn() {
        return registeredIn;
    }


    public boolean isUserValid() {
        return userValid;
    }

    public void setUserValid(boolean userValid) {
        this.userValid = userValid;
    }

    public boolean isUserRegistered(String username, String password){
        boolean found = false;
        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                found = true;
                return found;
            }
        }
        return found;
    }

    public boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false; // username is null or empty
        }
        if (Character.isDigit(username.charAt(0))) {
            return false; // username starts with a digit
        }

        List<Object> allUsers = getAllUsers();
        for (Object user : allUsers) {
            String existingUsername = getUsernameFromObject(user);
            if (existingUsername.equals(username)) {
                return false; // username already exists
            }
        }

        return true; // username is valid
    }

    // This is a helper method to extract the username from the object
    private String getUsernameFromObject(Object user) {
        // Assuming all user objects have a getUsername method
        if (user instanceof Admin admin) {
            return admin.getUsername();
        } else if (user instanceof User) {
            return ((User) user).getUsername();
        } else if (user instanceof StoreOwner) {
            return ((StoreOwner) user).getUsername();
        } else if (user instanceof RawSupplier) {
            return ((RawSupplier) user).getUsername();
        }
        return "";
    }

    public boolean isValidPassword(String password) {

        return password != null && password.length() >= 3;
    }

    public void registerUser(User user) {
        if (isUserValid()) {
            users.add(user);
            setMessage("User registered successfully!");
        } else {
            setMessage("Invalid user details!");
        }
    }

    public boolean isUserRegistered(User user){
        return users.contains(user);
    }

    public User isUserRegister(String username, String password) {
        User user = checkCredentials(users, username, password, 'U');
        if (user != null) return user;

        user = checkCredentials(admins, username, password, 'A');
        if (user != null) return user;

        user = checkCredentials(storeOwners, username, password, 'S');
        if (user != null) return user;

        user = checkCredentials(suppliers, username, password, 'R');
        return user;
    }

    private <T extends User> User checkCredentials(List<T> list, String username, String password, char role) {
        for (T user : list) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setRole(role);
                return user;
            }
        }
        return null;
    }


    public boolean isAddedInSystem(userType type, String username) {
        List<? extends User> userList = getUserListByType(type);

        if (userList != null) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<? extends User> getUserListByType(userType type) {
        return switch (type) {
            case USER -> users;
            case ADMIN -> admins;
            case STORE_OWNER -> storeOwners;
            case SUPPLIER -> suppliers;
            default -> null;
        };
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getAllUsers() {
        List<Object> allUsers = new ArrayList<>();
        // this method will return every user could use the system, no matter what the role is.
        allUsers.addAll(users);
        allUsers.addAll(storeOwners);
        allUsers.addAll(suppliers);
        allUsers.addAll(admins);
        return allUsers;
    }

    public HashMap<String, Integer> getUserStatisticsByCity() {
        HashMap<String, Integer> cityStatistics = new HashMap<>();
        String[] cities = {"Nablus", "Jenin", "Ramallah", "Jerusalem", "Jericho", "Tulkarem", "Hebron", "Qalqiliah", "Bethlehem", "Tubas"};
        for (String city : cities) {
            cityStatistics.put(city, 0);
        }
        for (User user : users) {
            String userCity = user.getCity();
            if (cityStatistics.containsKey(userCity)) {
                cityStatistics.put(userCity, cityStatistics.get(userCity) + 1);
            } else {
                cityStatistics.put(userCity, 1);
            }
        }
        return cityStatistics;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }



    public List<Post> getPosts() {
        return posts;
    }



    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        setRecipeAdded(true);
    }

    public void addPost(Post post) {
        posts.add(post);
        setPostAdded(true);
    }

    public boolean isRecipeAdded() {
        return recipeAdded;
    }

    public void setRecipeAdded(boolean recipeAdded) {
        this.recipeAdded = recipeAdded;
    }

    public boolean isPostAdded() {
        return postAdded;
    }

    public void setPostAdded(boolean postAdded) {
        this.postAdded = postAdded;
    }

    public boolean deleteRecipe(Recipe recipe) {
        boolean deleted = false;
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe r = iterator.next();
            if (r.getTitle().equals(recipe.getTitle()) && r.getDescription().equals(recipe.getDescription())) {
                iterator.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }


    public boolean deletePost(Post post) {
        boolean deleted = false;
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post p = iterator.next();
            if (p.getTitle().equals(post.getTitle()) && p.getContent().equals(post.getContent())) {
                iterator.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }


    public boolean editRecipe(String title, String description, Recipe unwantedRecipe) {
        Recipe newRecipe = new Recipe(title, description);
        boolean deleted = deleteRecipe(unwantedRecipe);
        newRecipe.setTitle("IceCreem");
        newRecipe.setDescription("Yummy");
        addRecipe(newRecipe);
        if (deleted && isRecipeAdded())
            return true;
        else return false;
    }

    public boolean editPost(String title, String content, Post unwantedPost) {
        Post newPost = new Post(title, content);
        boolean deleted = deletePost(unwantedPost);
        newPost.setTitle("IceCreem");
        newPost.setContent("Yummy");
        addPost(newPost);
        if (deleted && isPostAdded())
            return true;
        else return false;
    }

    public boolean isProductAdded() {
        return productAdded;
    }

    public void setProductAdded(boolean productAdded) {
        this.productAdded = productAdded;
    }

    public List<User> getUsers() {
        return users;
    }



    public boolean isMessageSent() {
        return messageSent;
    }

    public void setMessageSent(boolean messageSent) {
        this.messageSent = messageSent;
    }

    public List<StoreOwner> getStoreOwners() {
        return storeOwners;
    }



    public void sendMessageToUser(String message, String user) {
        for (User u : users) {
            if (u.getUsername().equals(user)) {
                u.addMessage(message);
                setMessageSent(true);
            }
        }
    }

    public void sendMessageToSupplier(String message, String s) {
        for (RawSupplier r : suppliers) {
            if (r.getUsername().equals(s)) {
                r.addMessage(message);
                setMessageSent(true);
            }
        }
    }

    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null; // Or throw an exception if user not found
    }

    public RawSupplier getSupplierByUsername(String username) {
        for (RawSupplier r : suppliers) {
            if (r.getUsername().equals(username)) {
                return r;
            }
        }
        return null; // Or throw an exception if user not found
    }

    public StoreOwner getStoreOwnerByUsername(String username) {
        for (StoreOwner owner : storeOwners) {
            if (owner.getUsername().equals(username)) {
                return owner;
            }
        }
        return null; // Return null if no matching owner is found
    }
    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null; // Return null if no matching owner is found
    }
    public StoreOwner getStoreOwnerByBusinessName(String username) {
        for (StoreOwner owner : storeOwners) {
            if (owner.getBusinessName().equals(username)) {
                return owner;
            }
        }
        return null; // Return null if no matching owner is found
    }

    public void enableEmailNotifications() {
        emailNotificationsEnabled = true;
    }

    public boolean isEmailNotificationsEnabled() {
        return emailNotificationsEnabled;
    }

    public void makeSpecialRequest(User user, StoreOwner owner, String requestContent) throws EmailSendingException {
        if (user == null || owner == null)
        {
            LOGGER.log(Level.SEVERE, "Connection couldn't be made, The User/Owner's info is missing!");
        }
        else {
            specialRequestMade = true;
            String content ="A Special request made by: "
                    + user.getUsername() + "\n\n" + requestContent
                    + "\n\nThe provided User email if further communication is required: "
                    + user.getEmail() + "\n";

            sendEmailNotification(content, owner.getEmail().trim());
        }
    }

    public boolean isSpecialRequestMade() {
        return specialRequestMade;
    }

    public void sendEmailNotification(String content, String toEmail) throws EmailSendingException {
        lastEmailNotificationContent = content;
        if (emailNotificationsEnabled) {
            EmailService.sendEmail(toEmail, "Special Request Notification", content);
        }
    }

    public String getLastEmailNotificationContent() {
        return lastEmailNotificationContent;
    }

    public boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }


    public String searchingList(String searchFor){

        String actualRecipe="" ;
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(searchFor)) {
                actualRecipe = recipe.toString();

            }
        }
        return actualRecipe;
    }
    public String searchingForNutrient(String nutrientType, String dietaryNeeds) {
        String actualDietaryNeeds = "";
        for (Recipe recipe : recipes) {
            String nutrientValue = getNutrientValue(recipe, nutrientType);
            if (nutrientValue != null && nutrientValue.equals(dietaryNeeds)) {
                actualDietaryNeeds = recipe.getTitle() + "\n" + recipe.getNutrient() + "\n";
                break; // Exit the loop once a match is found
            }
        }
        LOGGER.info(actualDietaryNeeds);
        return actualDietaryNeeds;
    }

    private String getNutrientValue(Recipe recipe, String nutrientType) {
        return switch (nutrientType) {
            case "Protein" -> recipe.getProtein();
            case "Fat" -> recipe.getFat();
            case "Sugar" -> recipe.getSugar();
            case "Calories" -> recipe.getCalories();
            default -> null;
        };
    }


    public String searchingForFoodAllergies(String searchForFoodAlergies){

        String actualFoodAllergies="" ;
        for (Recipe recipe : getRecipes()) {
            if (recipe.getFoodAllergies().equals(searchForFoodAlergies)) {
                continue;
            }
            actualFoodAllergies = actualFoodAllergies + recipe;

        }
        LOGGER.log(Level.INFO, "Actual food allergies: {0}", actualFoodAllergies);
        return actualFoodAllergies;
    }
    public String printListOfDietaryNeedsAndFoodAllergies(){

        String actualFoodAllergies="" ;
        for (Recipe recipe : recipes) {

            actualFoodAllergies = actualFoodAllergies +recipe.getTitle()+"\nAllergies: "+recipe.getFoodAllergies()+"\n"+recipe.getNutrient()+"\n";

        }
        LOGGER.log(Level.INFO, "Actual food allergies: {0}", actualFoodAllergies);
        return actualFoodAllergies;
    }


    public String getOptionList(){
        int counter = 1;
        String actual="Options: ";
        for (Recipe recipe : recipes) {
            actual =  actual +counter+"."+ recipe.getOption()+" ";
            counter++;
        }
        actual= actual + "\n";
        LOGGER.log(Level.INFO, "Actual value: {0}", actual);
        return actual;

    }
    public boolean registerUser(String name,String password,String email,String city){
        if(isValidUsername(name) && isValidPassword(password) && isEmailValid(email)) {
            User user = new User(name, password, email, city);
            addUserToFile("Users.txt",user);
            users.add(user); //adding the user to the list to keep it updated.
            return true;
        }
        else
            return false; //some condition was incorrect while signing up so false will be returned.
    }

    public void printStoreOwners() {
        int index = 1;
        for (StoreOwner o : storeOwners) {
            LOGGER.log(Level.INFO, "{0}. {1}", new Object[]{index, o.getBusinessName()});
            index++;
        }
    }


    public void addUserFeedback(Feedback feedback){
        feedbacks.add(feedback);
    }

    public void printUserFeedbacks() {
        for (Feedback fb : feedbacks) {
            // Log the product-related feedback
            LOGGER.log(Level.INFO, "Product Related: {0}", fb.getRelatedProduct());

            // Log the feedback content
            LOGGER.log(Level.INFO, "Feedback Content: {0}", fb.getMessage());
        }
    }


    public boolean removeStoreOwner(String username){
        boolean removed = false;
        Iterator<StoreOwner> iterator = storeOwners.iterator();
        while (iterator.hasNext()) {
            StoreOwner storeOwner = iterator.next();
            if (storeOwner.getUsername().equals(username)) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        return removed;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<RawSupplier> getSuppliers() {
        return suppliers;
    }

    public static List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}