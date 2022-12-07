package blockchain;
   
import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blockchain {

//Making  functions Global to also use in functions
//Stores the count of number of blocks
    public static int BlockNo = 0;
//Stores the user data/input
    public static String transaction;
//Stores the hashing value of the last second block
    public static String previousHash;
//Stores the hashing value of the lastest block
    public static String blockHash;
    public static Scanner input = new Scanner(System.in);
    public static List <String> blockList = new ArrayList <String>();

//Main function to convert the data into sha256 hash
//String p is previous hash and String q is transaction
    static void sha256(String p, String q)throws NoSuchAlgorithmException{
        long unixTime = System.currentTimeMillis() / 1000L;
        String timestamp = Long.toString(unixTime);
        String blockData = timestamp + p + q;         
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(blockData.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            blockHash = sb.toString();
            BlockNo++;
            previousHash = blockHash;
            blockList.add(blockHash);
            System.out.println("Block number = "+ BlockNo + ", Hash = " + blockHash);
    }
    
//Function to view the whole blockchain at once
    static void view(){
        int n = blockList.size();
        for(int i=0; i < n; i++){
            System.out.println("Block number: " + (i+1) + " ,Blockchain hash: "+ blockList.get(i));
        }
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        System.out.println("Enter the transaction: ");
        transaction = input.nextLine();
        sha256("0", transaction);
        while(TRUE){
        System.out.println("Type \"1\" = Add another Block , \"2\" = View the Blockchain, \"3\" to Exit");
        int decision = input.nextInt();

            if (decision == 1){
                System.out.println("Enter the transaction: ");
                transaction = input.next();
                sha256(previousHash, transaction);
            } else if (decision == 2) {
                view();
            } else if (decision == 3){
                System.out.println("You exitted!");
                break;
            }else {
                System.out.println("Wrong input type again!");
            }
        } 
    }
}