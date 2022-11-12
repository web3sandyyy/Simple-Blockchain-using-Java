package blockchain;
   
import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Blockchain {

//Making variables Global

    public static int BlockNo = 0;
    public static String transaction;
    public static Scanner s = new Scanner(System.in);
    public static int previousHash;
    public static List <Integer> blocklist = new ArrayList <Integer>();

//Main function to create a hash
    
    static void Block (int previousHash, String transactions){
        Object [] con = {transactions, previousHash};
        int blockHash = Arrays.hashCode(con);
        BlockNo++;
        previousHash = blockHash;
        blocklist.add(blockHash);
        System.out.println("Block number = "+ BlockNo + ", Hash = " + blockHash);
    }
    
//Function to see the Block Hash and Block number

    static void view(){
        int n = blocklist.size();
        for(int i=0; i < n; i++){
            System.out.println("Block number: " + (i+1) + " ,Blockchain hash: "+ blocklist.get(i));
        }
    }
    
    public static void main(String[] args) {

//Creating the Genesis Block

        System.out.println("Enter the transaction: ");
        transaction = s.nextLine();
        Block(0, transaction);
        
        while(TRUE){
        System.out.println("Type \"1\" = Add another Block , \"2\" = View the Blockchain, \"3\" to Exit");
        int decision = s.nextInt();

            if (decision == 1){
                System.out.println("Enter the transaction: ");
                transaction = s.next();
                Block(previousHash, transaction);
            } else if (decision == 2) {
                view();
            } else if (decision == 3){
                System.out.println("You exitted!");
                break;
            }else {
                System.out.println("Wrong input type again!");
                continue;
            }
        } 
    }
}
    
