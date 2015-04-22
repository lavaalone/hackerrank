package findcommonancestor;

/**
 * Created by thinhnguyen on 4/20/15.
 */

import java.util.*;

public class MyFindCommonAncestor implements  FindCommonAncestor{
    @Override
    public String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2) {
        boolean[] indexesCommit1 = markMatchingIndexes(commitHash1, commitHashes, parentHashes);
        boolean[] indexesCommit2 = markMatchingIndexes(commitHash2, commitHashes, parentHashes);

        for (int i = 0; i < commitHashes.length; i++)
            if (indexesCommit1[i] && indexesCommit2[i])
                return commitHashes[i];

        return null;
    }

    private void addToCommitPath(Set<String> commitPathSet, int beginingIndex, String commitHash, String[] commitHashes, String[][] parentHashes) {
        int index = beginingIndex;
        while( index < commitHashes.length ) {

            if (commitHashes[index].equals(commitHash)) { //check if commit exists in the parent's commits array then take out its index from parent
                commitPathSet.add(commitHashes[index]);
                break; //if commit found in parent then get it and break further loop iteration
            }
            index++ ;
        }

        if (parentHashes[index] != null) { //make sure index is not null i.e. before any initial/first commit
            for (String parent : parentHashes[index]) { //loop over and collect all corresponding matching commits from parent's commit array
                if (!commitPathSet.contains(parent))
                    addToCommitPath(commitPathSet, index, parent, commitHashes, parentHashes);
            }
        }
    }

    private boolean[] markMatchingIndexes(String commitHash, String[] commitHashes, String[][] parentHashes)    {
        Set<String> allCommitPathsSet = new HashSet<String>();
        addToCommitPath(allCommitPathsSet, 0, commitHash, commitHashes, parentHashes);
        boolean[] indexesCommit1 = new boolean[commitHashes.length];
        for (int i = 0; i < commitHashes.length; i++) {
            indexesCommit1[i] = allCommitPathsSet.contains(commitHashes[i]);
        }
        return indexesCommit1;
    }

    private List<String> CommitPath(String s, String[] commits, String[][] parents) {
        List<String> r = new LinkedList<String>();
        List<String> c = Arrays.asList(commits);
        List<String[]> p = new LinkedList<String[]>();
        for (String[] aos : parents) {
            p.add(aos);
        }
        do {
            int idx = c.indexOf(s);
            if (idx == -1)
                break;

            String[] aos = p.get(idx);
            if (aos == null)
                break;

        } while (true);
        return r;
    }

    public static void main(String[] args)   {
        String[] commits = { "G", "F", "E", "D", "C", "B", "A" };
        String[][] parents = { { "F", "D" }, { "E" }, { "B" }, { "C" }, { "B" }, { "A" }, null };
        String commit1 = "G";
        String commit2 = "B";

        MyFindCommonAncestor myFindCommonAncestor = new MyFindCommonAncestor();
        System.out.println("Common Ancestor is " + myFindCommonAncestor.findCommmonAncestor(commits, parents, commit1, commit2));
    }


}
