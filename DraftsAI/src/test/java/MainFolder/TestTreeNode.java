package MainFolder;



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
public class TestTreeNode {

    int data;
    TreeNode parent;
    List<TestTreeNode> children;

    public TestTreeNode(int data) {
        this.data = data;
        this.children = new LinkedList<TestTreeNode>();
    }

    public TestTreeNode addChild(int data) {
        TestTreeNode childNode = new TestTreeNode(data);
        //childNode.parent = this.parent;
        //this.children.add(childNode);
        this.addChild(childNode);
        return  childNode;
    }
    public void addChild(TestTreeNode node) {
        node.parent = this.parent;
        this.children.add(node);
    }
    

    
    public List<TestTreeNode> getChildren(){
        return children;
    }
    
    public void displayNodeData(TestTreeNode node){
            

                            System.out.print(node.data + " ");

        
    }   
    
    public void traverse(TestTreeNode node){
System.out.println("Node "+ node.data+" has "+node.children.size()+" children");


        for (int i = 0; i < node.children.size(); i++) {
            System.out.println("CHILDREN");
            System.out.println(node.children.get(i).data);
        }

        if(node !=null)
        {
            for (int i = 0; i<node.children.size();i++){
                //System.out.println("");
                traverse(node.children.get(i));
            }
        }
    }



}
