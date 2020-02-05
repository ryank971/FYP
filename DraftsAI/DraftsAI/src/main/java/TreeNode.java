
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
public class TreeNode {

    int[][] data;
    TreeNode parent;
    List<TreeNode> children;

    public TreeNode(int[][] data) {
        this.data = data;
        this.children = new LinkedList<TreeNode>();
    }

    public TreeNode addChild(int[][] data) {
        TreeNode childNode = new TreeNode(data);
        //childNode.parent = this.parent;
        //this.children.add(childNode);
        this.addChild(childNode);
        return  childNode;
    }
    public void addChild(TreeNode node) {
        node.parent = this.parent;
        this.children.add(node);
    }
    

    
    public List<TreeNode> getChildren(){
        return children;
    }
    
    public void displayNodeData(TreeNode node){
            
        //System.out.println("this Node has "+node.children.size()+" children"+"with a value of: "+ MoveCalculator.evaluationFucntion(node.data));
                            for (int[] x : node.data) {
                        for (int y : x) {
                            System.out.print(y + " ");
                        }
                        System.out.println();
        }
    }   
    
    public void traverse(TreeNode node){
        
        if(node !=null)
        {
            for (int i = 0; i<node.children.size();i++){
                displayNodeData(node.children.get(i));
                System.out.println("");
                traverse(node.children.get(i));
            }
        }
    }

    //public TreeNode<T> removeChild(T data)
    
    //public TreeNode<T> findChild(T data)
    
    //
    
    // other features ...

}
