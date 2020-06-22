//1. Top K Frequently Mentioned Keywords

    public String [] getMostFrequentCommonwords(String[] reviews, String[] keywords, int k) {
        Map<String, Integer> frequencies = new HashMap<>();
        List<String> keywordList = Arrays.asList(keywords);

        for (String review : reviews) {
            review = review.toLowerCase();
            Set<String> encounteredWords = new HashSet<>();
            for (final String reviewWord : review.split(" ")) {
                if (!encounteredWords.contains(reviewWord) && keywordList.contains(reviewWord)) {
                    Integer currentFrequency = frequencies.getOrDefault(reviewWord, 0);
                    frequencies.put(reviewWord, currentFrequency + 1);
                    encounteredWords.add(reviewWord);
                }
            }
        }

        String[] frequencyArray = frequencies.keySet().toArray(new String[k]);
        Arrays.sort(frequencyArray, (a,b) -> frequencies.get(a).equals(frequencies.get(b))
            ? a.compareTo(b)
            :frequencies.get(b) - frequencies.get(a) );

        return Arrays.copyOfRange(frequencyArray, 0, k);
    }


// Zombie in a Matrix

private static int minDays(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        Queue<int[]> queue = new LinkedList<>();
        int days = 0;
        int humanCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    humanCount++;
                } else {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty() && humanCount > 0) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] zombie = queue.poll();
                for (int[] dir : directions) {
                    int newX = zombie[0] + dir[0];
                    int newY = zombie[1] + dir[1];

                    if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[newX][newY] == 0) {   // when the new coordinates doesn't exceed the boundaries of the matrix or the new valid coordinate is a human, only then turn that to a zombie
                        matrix[newX][newY] = 1;
                        queue.offer(new int[]{newX, newY});   // now that new coordinate is a zombie, add that to the queue so it can be processed in the next level
                        humanCount--;
                    }
                }
            }
            days++;
        }
        return humanCount == 0 ? days : -1;
    }


//
