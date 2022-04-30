import java.io.*;
import java.util.*;
public class minimax
{
  public static int decide(int cur,int index,boolean max,int weight[],int height)
  {
    int num=0;
    //If we reach a leaf node
    if(cur==height)
      return weight[index];
    //If the node is a max one we find the max from sub-nodes
    if(max)
    num=Math.max(decide(cur+1,index*2,false,weight,height),decide(cur+1,index*2+1,false,weight,height));
      //If it is a min node we minimize the value under the sub-tree
      else
      num=Math.min(decide(cur+1,index*2,true,weight,height),decide(cur+1,index*2+1,true,weight,height));
      return num;
  }
  public static void main(String args[])
  {
    int scor[] = {3, 15, 2, 10, 12, 5, 2, 23};
    int n= scor.length;
    int h= (int)(Math.log(n) / Math.log(2));
    int a=decide(0,0,true,scor,h);
    System.out.println(a);
  }
}
