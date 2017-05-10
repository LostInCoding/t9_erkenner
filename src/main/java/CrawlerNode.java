import java.util.ArrayList;
import java.util.List;

public class CrawlerNode {
    private DataContainer data = null;
    private int id;
    private ArrayList<CrawlerNode> children = new ArrayList<>();
    private CrawlerNode parent = null;
    public static int idcount=1;
    public CrawlerNode(DataContainer data) {
        this.data = data;
        id=idcount;
        idcount++;
    }



    public void addChild(CrawlerNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(DataContainer data) {
        CrawlerNode newChild = new CrawlerNode(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<CrawlerNode> children) {
        for (CrawlerNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public ArrayList<CrawlerNode> getChildren() {
        return children;
    }

    public DataContainer getData() {
        return data;
    }

    public void setData(DataContainer data) {
        this.data = data;
    }

    private void setParent(CrawlerNode parent) {
        this.parent = parent;
    }

    public CrawlerNode getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + data.toString());
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1)
                    .print(prefix + (isTail ? "    " : "│   "), true);
        }
    }


    public CrawlerNode getChild(char c) {


        for (CrawlerNode child : children) {
            if (child.getData().getData() == c) {
                return child;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }
}