package Sweet.System;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SweetSystem {
    private boolean registeredIn;
    private boolean UserValid;
    private String message;
    private boolean recipeAdded;
    private boolean postAdded;
    private boolean productAdded;
    private boolean messageSent;
    private boolean listAsExpected;
    private boolean emailNotificationsEnabled;
    private boolean specialRequestMade;
    private String lastEmailNotificationContent;
    private EmailService emailService;
    public ArrayList<User> Users = new ArrayList<User>();
    public ArrayList<Admin> Admins = new ArrayList<Admin>();
    public ArrayList<StoreOwner> storeOwners = new ArrayList<StoreOwner>();
    public ArrayList<RawSupplier> Suppliers = new ArrayList<RawSupplier>();
    public ArrayList<Post> Posts = new ArrayList<Post>();
    public ArrayList<Recipe> Recipes = new ArrayList<Recipe>();
    public static ArrayList<Feedback> Feedbacks = new ArrayList<Feedback>();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BOLD = "\u001B[1m";

    public SweetSystem() throws IOException {
        registeredIn = false;
        UserValid = false;
        recipeAdded = false;
        postAdded = false;
        productAdded = false;
        messageSent = false;
        final String UsersFile = "Users.txt";
        final String AdminsFile = "Admins.txt";
        final String StoreOwnersFile = "StoreOwners.txt";
        final String SuppliersFile = "Suppliers.txt";
        final String ProductsFile = "Products.txt";
        final String OrdersFile = "Orders.txt";

        this.Users = loadUsersFromFile(UsersFile);
        this.Admins = loadAdminsFromFile(AdminsFile);
        this.storeOwners = loadStoreOwnersFromFile(StoreOwnersFile);
        this.Suppliers = loadSuppliersFromFile(SuppliersFile);
        StoreOwner.loadProductsFromFile(ProductsFile);
//        User.loadOrdersFromFile(OrdersFile);

        User Zahi = new User("User1", "123", "user1@example.com", "Nablus");


        Feedback zahiQudo = new Feedback("Chocolate Cake Was Crazy\n");
        Feedbacks.add(zahiQudo);

        Feedback feedback = new Feedback("The sweets are awesome, the place was quite and cosy, and the service was perfect, 10/10 Sweet shop!");
        Zahi.setUserFeedback(feedback);

        Order order = new Order("ChocolateCake", 2, "12345");
        Zahi.addOrder(order);
        Users.add(Zahi);
        Admin Hadi = new Admin("Admin", "Admin");
        Admins.add(Hadi);

        StoreOwner Khaled = new StoreOwner("StoreOwner1", "SO1", "MedbLucifer@gmail.com");
        Khaled.setAddress("Nablus city, West Bank, Palestine");
        Khaled.setBusinessName("BearBulk Co.");
        Khaled.addMessage("problem message  store Owner 1\n");
        storeOwners.add(Khaled);

        RawSupplier Ahmad = new RawSupplier("Supplier1", "RMS1", "supplier1@example.com");
        Ahmad.addMessage("problem message Supplier1\n");
        Suppliers.add(Ahmad);

        Product product1 = new Product("ChocolateCake", 10, 5);
        product1.setDiscount(15.0);
        product1.setSellingTimes(5);
        product1.setDescription("ChocolateIsVeryTasty!");
        Khaled.products.add(product1);


        Recipe recipe1 = new Recipe("Kunafa", "dough");
        recipe1.setOption( "Popular sweets");
        recipe1.setProtein("51g");
        recipe1.setFat("221g");
        recipe1.setSugar("741g");
        recipe1.setCalories("3105");
        recipe1.setFoodAllergies("Butter");
        Recipes.add(recipe1);

        Recipe recipe2 = new Recipe("Chocolate Cake", "aCakeFlavoredWithMeltedChocolate, cocoa powder");
        recipe2.setOption( "Cake");
        recipe2.setProtein("5g");
        recipe2.setFat("22g");
        recipe2.setSugar("74g");
        recipe2.setCalories("305");
        recipe2.setFoodAllergies("dairy");
        Recipes.add(recipe2);

        Post post1 = new Post("Kunafa", "dough");
        Posts.add(post1);

    }

    private ArrayList<User> loadUsersFromFile(String filename) {
        ArrayList<User> userList = new ArrayList<>();

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
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }
    private ArrayList<Admin> loadAdminsFromFile(String filename) {
        ArrayList<Admin> adminList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminData = line.split(" ");
                if (adminData.length == 2) {
                    String username = adminData[0];
                    String password = adminData[1];

                    Admin admin = new Admin(username, password);
                    adminList.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adminList;
    }
    public boolean addAdminToFile(String filename, Admin admin) {
        boolean isAdded = false;

        // Add the admin to the in-memory list
        Admins.add(admin);

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
    public ArrayList<StoreOwner> loadStoreOwnersFromFile(String fileName) {
        ArrayList<StoreOwner> storeOwners = new ArrayList<>();

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
                    System.out.println("Invalid data format in file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return storeOwners;
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
    public boolean deleteStoreOwnerFromFile(String fileName, String username) {
        boolean isDeleted = false;



        StoreOwner storeOwnerToRemove = null;
        for (StoreOwner storeOwner : storeOwners) {
            if (storeOwner.getUsername().equals(username)) {
                storeOwnerToRemove = storeOwner;
                break;
            }
        }

        if (storeOwnerToRemove != null) {
            storeOwners.remove(storeOwnerToRemove);
            isDeleted = true;
        } else {
            return false; // Store owner not found
        }
        removeStoreOwnerFromList(username); //remove the storeOwner from the list too.
        // Step 3: Rewrite the file with the remaining store owners
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (StoreOwner storeOwner : storeOwners) {
                String storeOwnerData = storeOwner.getUsername() + " " +
                        storeOwner.getPassword() + " " +
                        storeOwner.getEmail() + " " +
                        storeOwner.getBusinessName() + " " +
                        storeOwner.getAddress();
                bw.write(storeOwnerData);
                bw.newLine(); // Add a newline after writing each store owner
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return isDeleted;
    }

    public ArrayList<RawSupplier> loadSuppliersFromFile(String filename) {
        ArrayList<RawSupplier> supplierList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] supplierData = line.split(" ");
                if (supplierData.length == 3) {
                    String username = supplierData[0];
                    String password = supplierData[1];
                    String email = supplierData[2];

                    RawSupplier supplier = new RawSupplier(username, password, email);
                    supplierList.add(supplier);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return supplierList;
    }
    public boolean addSupplierToFile(String filename, RawSupplier supplier) {
        boolean isAdded = false;

        Suppliers.add(supplier);

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

    public void setRegisteredIn(boolean registeredIn) {
        this.registeredIn = registeredIn;
    }

    public boolean isUserValid() {
        return UserValid;
    }

    public void setUserValid(boolean userValid) {
        UserValid = userValid;
    }

    public boolean isUserRegistered(String username, String password){
        boolean found = false;
        for (User user : Users) {
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
        if (user instanceof Admin) {
            return ((Admin) user).getUsername();
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
            Users.add(user);
            setMessage("User registered successfully!");
        } else {
            setMessage("Invalid user details!");
        }
    }

    public boolean isUserRegistered(User user){
        return Users.contains(user);
    }

    public User isUserRegister(String username, String password) {
        User user = checkCredentials(Users, username, password, 'U');
        if (user != null) return user;

        user = checkCredentials(Admins, username, password, 'A');
        if (user != null) return user;

        user = checkCredentials(storeOwners, username, password, 'S');
        if (user != null) return user;

        user = checkCredentials(Suppliers, username, password, 'R');
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
        switch (type) {
            case USER:
                for (User user : Users) {
                    if (user.getUsername().equals(username)) {
                        return true;
                    }
                }
                break;
            case ADMIN:
                for (Admin admin : Admins) {
                    if (admin.getUsername().equals(username)) {
                        return true;
                    }
                }
                break;
            case STORE_OWNER:
                for (StoreOwner storeOwner : storeOwners) {
                    if (storeOwner.getUsername().equals(username)) {
                        return true;
                    }
                }
                break;
            case SUPPLIER:
                for (RawSupplier sp : Suppliers) {
                    if (sp.getUsername().equals(username)) {
                        return true;
                    }
                }
                break;
            default:
                return false;
        }
        return false;
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
        allUsers.addAll(Users);
        allUsers.addAll(storeOwners);
        allUsers.addAll(Suppliers);
        allUsers.addAll(Admins);
        return allUsers;
    }

    public HashMap<String, Integer> getUserStatisticsByCity() {
        HashMap<String, Integer> cityStatistics = new HashMap<>();
        String[] cities = {"Nablus", "Jenin", "Ramallah", "Jerusalem", "Jericho", "Tulkarem", "Hebron", "Qalqiliah", "Bethlehem", "Tubas"};
        for (String city : cities) {
            cityStatistics.put(city, 0);
        }
        for (User user : Users) {
            String userCity = user.getCity();
            if (cityStatistics.containsKey(userCity)) {
                cityStatistics.put(userCity, cityStatistics.get(userCity) + 1);
            } else {
                cityStatistics.put(userCity, 1);
            }
        }
        return cityStatistics;
    }

    public ArrayList<Recipe> getRecipes() {
        return Recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        Recipes = recipes;
    }

    public ArrayList<Post> getPosts() {
        return Posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        Posts = posts;
    }

    public void addRecipe(Recipe recipe) {
        Recipes.add(recipe);
        setRecipeAdded(true);
    }

    public void addPost(Post post) {
        Posts.add(post);
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
        Iterator<Recipe> iterator = Recipes.iterator();
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
        Iterator<Post> iterator = Posts.iterator();
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


    public boolean editRecipe(String Title, String Description, Recipe unwantedRecipe) {
        Recipe newRecipe = new Recipe(Title, Description);
        boolean deleted = deleteRecipe(unwantedRecipe);
        newRecipe.setTitle("IceCreem");
        newRecipe.setDescription("Yummy");
        addRecipe(newRecipe);
        if (deleted && isRecipeAdded())
            return true;
        else return false;
    }

    public boolean editPost(String Title, String Content, Post unwantedPost) {
        Post newPost = new Post(Title, Content);
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

    public ArrayList<User> getUsers() {
        return Users;
    }

    public ArrayList<Admin> getAdmins() {
        return Admins;
    }

    public boolean isMessageSent() {
        return messageSent;
    }

    public void setMessageSent(boolean messageSent) {
        this.messageSent = messageSent;
    }

    public ArrayList<StoreOwner> getStoreOwners() {
        return storeOwners;
    }

    public ArrayList<RawSupplier> getSuppliers() {
        return Suppliers;
    }

    public void sendMessageToUser(String message, String user) {
        for (User u : Users) {
            if (u.getUsername().equals(user)) {
                u.addMessage(message);
                setMessageSent(true);
            }
        }
    }

    public void sendMessageToSupplier(String message, String s) {
        for (RawSupplier r : Suppliers) {
            if (r.getUsername().equals(s)) {
                r.addMessage(message);
                setMessageSent(true);
            }
        }
    }

    public User getUserByUsername(String username) {
        for (User u : Users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null; // Or throw an exception if user not found
    }

    public RawSupplier getSupplierByUsername(String username) {
        for (RawSupplier r : Suppliers) {
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
        for (Admin admin : Admins) {
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

    public void makeSpecialRequest(User user, StoreOwner owner, String requestContent) {
        if (user == null || owner == null)
        {
            System.out.println(ANSI_RED + "Connection couldn't be made, The User/Owner's info is missing!" + ANSI_RESET);
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

    public void sendEmailNotification(String content, String toEmail) {
        lastEmailNotificationContent = content;
        if (emailNotificationsEnabled) {
            EmailService.sendEmail(toEmail, "Special Request Notification", content);
        }
    }

    public String getLastEmailNotificationContent() {
        return lastEmailNotificationContent;
    }

    public boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void printSearchingList(String searchFor){
        System.out.println(SearchingList(searchFor));

    }
    public String SearchingList(String searchFor){

        String actualRecipe="" ;
        for (Recipe recipe : Recipes) {
            if (recipe.getTitle().equals(searchFor)) {
                actualRecipe = recipe.toString();

            }
        }
        return actualRecipe;
    }
    public String SearchingForNutrient(String searchForNutrient1,String dietaryNeeds){

        String actualDietaryNeeds="" ;

        if (searchForNutrient1.equals("Protein")) {
            for (Recipe recipe : Recipes) {
                if (recipe.getProtein().equals(dietaryNeeds)) {
                    actualDietaryNeeds = recipe.getTitle()+"\n"+recipe.getNutrient()+"\n";

                }
            }
        }
        if (searchForNutrient1.equals("Fat")){
                for (Recipe recipe : Recipes) {
                    if(recipe.getFat().equals(dietaryNeeds)) {
                        actualDietaryNeeds = recipe.getTitle()+"\n"+recipe.getNutrient()+"\n";
                    }
                }
            }
        if (searchForNutrient1.equals("Sugar")){
            for (Recipe recipe : Recipes) {
                if(recipe.getSugar().equals(dietaryNeeds)) {
                    actualDietaryNeeds = recipe.getTitle()+"\n"+recipe.getNutrient()+"\n";
                }
            }
        }
        if (searchForNutrient1.equals("Calories")){
            for (Recipe recipe : Recipes) {
                if(recipe.getCalories().equals(dietaryNeeds)) {
                    actualDietaryNeeds = recipe.getTitle()+"\n"+recipe.getNutrient()+"\n";
                }
            }
        }


        System.out.println(actualDietaryNeeds);

        return actualDietaryNeeds;
    }

    public String SearchingForFoodAlergies(String searchForFoodAlergies){

        String actualFoodAlergies="" ;
            for (Recipe recipe : Recipes) {
                if (recipe.getFoodAllergies().equals(searchForFoodAlergies)) {
                    continue;
                }
                actualFoodAlergies = actualFoodAlergies +recipe.toString();

            }
        System.out.println(actualFoodAlergies);
        return actualFoodAlergies;
    }
    public String PrintListOfDietaryNeedsAndFoodAlergies(){

        String actualFoodAlergies="" ;
        for (Recipe recipe : Recipes) {

            actualFoodAlergies = actualFoodAlergies +recipe.getTitle()+"\nAllergies: "+recipe.getFoodAllergies()+"\n"+recipe.getNutrient()+"\n";

        }
        System.out.println(actualFoodAlergies);
        return actualFoodAlergies;
    }


    public String getOptionList(){
        int counter=1;
        String actual="Options: ";
        for (Recipe recipe : Recipes) {
            actual=  actual +counter+"."+ recipe.getOption()+" ";
            counter++;
            }
        actual= actual + "\n";
        System.out.println(actual);
        return actual;

    }
    public boolean registerUser(String name,String password,String email,String city){
        if(isValidUsername(name) && isValidPassword(password) && isEmailValid(email)) {
            User user = new User(name, password, email, city);
            addUserToFile("Users.txt",user);
            Users.add(user); //adding the user to the list to keep it updated.
            return true;
        }
        else
            return false; //some condition was incorrect while signing up so false will be returned.
    }

    public void printStoreOwners(){
        int index = 1;
        for (StoreOwner o : storeOwners){
            System.out.println(index + ". " + o.getBusinessName());
            index++;
        }
    }

    public void addUserFeedback(Feedback feedback){
        Feedbacks.add(feedback);
    }

    public void printUserFeedbacks(){
        for (Feedback fb : Feedbacks) {
            System.out.println(ANSI_WHITE + ANSI_BOLD + "Product Related: " + fb.getRelatedProduct() + "\n");
            System.out.println("Feedback Content: "+ fb.getFeedback() + ANSI_RESET);
        }
    }


}