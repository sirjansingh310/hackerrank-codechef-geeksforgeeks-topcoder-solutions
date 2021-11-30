// https://leetcode.com/problems/accounts-merge/
class Solution {
    private void mergeClusters(Map<Integer, List<String>> map, List<String> toAdd, Set<Integer> clusterIds, int clusterId, Map<String, Integer> emailToCluster) {
        
        for (Integer id : clusterIds) {
            List<String> original = map.get(id);
            toAdd.addAll(original);
            map.put(id, new ArrayList<>());
        }
        
        List<String> original = map.getOrDefault(clusterId, new ArrayList<>());
        original.addAll(toAdd);
        map.put(clusterId, new ArrayList<>(new TreeSet<>(original)));
        
        List<String> merged = map.get(clusterId);
        for (String s : merged) {
            emailToCluster.put(s, clusterId);
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, String> clusterToName = new HashMap<>();
        Map<String, Integer> emailToCluster = new HashMap<>();
        Map<Integer, List<String>> clusterToEmail = new HashMap<>();
        
        int cluster = 1;
        for (List<String> list : accounts) {
            String name = list.get(0);
            List<String> toAdd = new ArrayList<>();
            Set<Integer> clustersToMerge = new TreeSet<>();
            int clusterId = -1;
            for (int i = 1; i < list.size(); i++) {
                if (emailToCluster.get(list.get(i)) != null) {
                    clustersToMerge.add(emailToCluster.get(list.get(i)));
                }
                toAdd.add(list.get(i));
            }
            if (clustersToMerge.isEmpty()) {
                clusterId = cluster;
                cluster++;
            } else {
                clusterId = clustersToMerge.iterator().next();
            }
            
            clusterToName.put(clusterId, name);
            
            mergeClusters(clusterToEmail, toAdd, clustersToMerge, clusterId, emailToCluster);
        }
        
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : clusterToEmail.entrySet()) {
            if (entry.getValue() != null && entry.getValue().size() > 0) {
                String name = clusterToName.get(entry.getKey());
                List<String> toAdd = new ArrayList<>();
                toAdd.add(name);
                toAdd.addAll(entry.getValue());
                result.add(toAdd);
            }
        }
        return result;
    }
}
