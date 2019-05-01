import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisjointSets {
   // private instance variable namesDisjointSet
   private List<Map<String, Set<String>>> namesDisjointSet;

// constructor
   public DisjointSets() {
       namesDisjointSet = new ArrayList<Map<String, Set<String>>>();
   }


/**

   * Make a set given a person's name

   * @param name

   */
   public void makeSet(String name) {
       Map<String, Set<String>> namesMap = new HashMap<String, Set<String>>();
       Set<String> namesSet = new HashSet<String>();
       namesSet.add(name); // add name to namesSet
       namesMap.put(name, namesSet); // add mapping of name and namesSet
       namesDisjointSet.add(namesMap); // add namesMap to disjointSet
   }
  
   /**

   * Return the rep of the set the name is part of

   * @param name person's name

   * @return representative of that person's set

   */

   public String find(String name) {
       for (int i = 0; i < namesDisjointSet.size(); i++) {
           Map<String, Set<String>> namesMap = namesDisjointSet.get(i);
           Set<String> keyNameSet = namesMap.keySet();
           for (String keyName : keyNameSet) { // iterate through keyset
               Set<String> namesSet = namesMap.get(keyName);
               if (namesSet.contains(name)) { // if found return key
                   return keyName;
               }
           }
       }
       return null; // else return null
   }


/**

   * Union the set of two people

   * @param name1 first person's name

   * @param name2 second person's name

   */
  
   public void union(String name1, String name2) {
       String firstRep = find(name1); // find first representative
       String secondRep = find(name2); // find second representative

       Set<String> firstNameSet = null;
       Set<String> secondNameSet = null;

       for (int index = 0; index < namesDisjointSet.size(); index++) {
           Map<String, Set<String>> map = namesDisjointSet.get(index);
           if (map.containsKey(firstRep)) {
               firstNameSet = map.get(firstRep); // set value for firstNameSet
           } else if (map.containsKey(secondRep)) {
               secondNameSet = map.get(secondRep); // set value for secondNameSet
           }
       }
      

       if (firstNameSet != null && secondNameSet != null)
           firstNameSet.addAll(secondNameSet); // do union by adding all from secondNumSet to firstNameSet

      
       for (int j = 0; j < namesDisjointSet.size(); j++) {
           Map<String, Set<String>> map = namesDisjointSet.get(j);
           if (map.containsKey(firstRep)) {
               map.put(firstRep, firstNameSet);
           } else if (map.containsKey(secondRep)) {
               map.remove(secondRep); // remove secondRep from map
               namesDisjointSet.remove(j); // remove jth element
           }
       }

       return;
   }

   public static void main(String... arg) {
       DisjointSets disjointSet = new DisjointSets();

       String[] names = { "Tasha", "Rui", "Tenzin", "Liz", "Nev", "Loan", "George" }; // string array of names

       for (int i = 0; i < names.length; i++) {
           disjointSet.makeSet(names[i]); // make set using names array of strings
       }

       System.out.println("Representation of elements");
       for (int i = 0; i < names.length; i++) {
           System.out.println(i + "\t:\t" + disjointSet.find(names[i])); // find each string
       }

       disjointSet.union("Liz", "Loan"); // union Liz an Loan
       disjointSet.union("Loan", "Tasha"); // union Loan and Tasha

      
       System.out.println("Union of Liz & Loan and Loan & Tasha ");

       System.out.println("Representation of elements");
       for (int i = 0; i < names.length; i++) {
           System.out.println(i + "\t:\t" + disjointSet.find(names[i]));
       }

   }
}
