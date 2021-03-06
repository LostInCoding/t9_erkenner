package t9;

import java.util.ArrayList;
import java.util.List;

public class T9Node<T> {
    private T data = null;
    private List<T9Node<T>> children = new ArrayList<>();
    private T9Node<T> parent = null;

    public T9Node(T data) {
        this.data = data;
    }

    public void addChild(T9Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        T9Node<T> newChild = new T9Node<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<T9Node<T>> children) {
        for (T9Node<T> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<T9Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(T9Node<T> parent) {
        this.parent = parent;
    }

    public T9Node<T> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }


    public void print() {
        System.out.println(getStringRepresentation());
    }

    public String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        getStringRepresentation("", sb, true);
        return sb.toString();
    }

    private void getStringRepresentation(String prefix, StringBuilder sb, boolean isTail) {
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(data.toString()).append("\n");
        for (int i = 0; i < children.size() - 1; i++) {
            ((T9Node<T>) children.get(i)).getStringRepresentation(prefix + (isTail ? "    " : "│   "), sb, false);
        }
        if (children.size() > 0) {
            ((T9Node<T>) children.get(children.size() - 1))
                    .getStringRepresentation(prefix + (isTail ? "    " : "│   "), sb, true);
        }
    }

    public String getHistory(int historySize) {
        StringBuilder history = new StringBuilder();
        T9Node<T9DataContainer> actnode = (T9Node<T9DataContainer>) getParent();
        for (int i = historySize; i > 0; i--) {
            if (actnode.getParent()==null) {
                break;
            }
            history.append(actnode.getData().getChar());
            actnode = actnode.getParent();


        }
        return history.toString();
    }

    @Override
    public String toString() {
        T9Node node = this;
        StringBuilder res = new StringBuilder();
        while (node.getParent() != null) {
            res.append(node.data.toString());
        }
        return res.toString();
    }
}