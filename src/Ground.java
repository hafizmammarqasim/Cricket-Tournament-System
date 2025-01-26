public class Ground {
    private String groundName;
    private String city;
    private String seatingCapacity;
    private String groundLength;

    public Ground(String groundName,String city,String seatingCapacity, String groundLength) {
        this.groundName = groundName;
        this.seatingCapacity = seatingCapacity;
        this.groundLength = groundLength;
        this.city =  city;
    }

    public String writeToFile(){
        return groundName+","+city+","+seatingCapacity+","+groundLength+"\n";
    }

    public String getGroundName(){
        return groundName;
    }

    public String getCity(){
        return city;
    }

    public void displayDetails(){
        System.out.println(groundName+"  "+ city+"  "+seatingCapacity+"  "+groundLength);
    }
}
