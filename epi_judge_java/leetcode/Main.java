package leetcode;

import epi.test_framework.minimal_json.JsonArray;
import epi.test_framework.minimal_json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {

    }

    private String[] getMovieTitlesFromApi(String substr) throws IOException {
        String response;
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=";
        String query = "&page=";
        int initialNumberOfPages = 1;
        int allNumberOfPages = Integer.MAX_VALUE; // assumption about number of available pages
        List<String> movieTitles = new ArrayList<>();


        while (initialNumberOfPages < allNumberOfPages) {
            URL fullUrl = new URL(url + substr + query + initialNumberOfPages);

            HttpURLConnection httpURLConnection = (HttpURLConnection) fullUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while ((response = in.readLine()) != null) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                allNumberOfPages = jsonObject.get("total_pages").getAsInt();

                JsonArray jsonArray = jsonObject.getAsJsonArray("data");

                for (int i = 0; i < jsonArray.size(); i++) {
                    String title = jsonArray.get(i).getAsJsonObject().get("Title").getAsString();
                    movieTitles.add(title);
                }
            }

            in.close();
            initialNumberOfPages++;


        }
        Collections.sort(movieTitles);
        return movieTitles.toArray(new String[0]);
    }

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }

    public List<String> reorderLogFiles(List<String> logs) {
        Collections.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }

    private List<List<Integer>> getPairs(List<List<Integer>> foregroundAppList, List<List<Integer>> backgroundAppList, int deviceCapacity) {
        Collections.sort(foregroundAppList, (i, j) -> i.get(1) - j.get(1));
        Collections.sort(backgroundAppList, (i, j) -> i.get(1) - j.get(1));

        List<List<Integer>> result = new ArrayList<>();


        int max = Integer.MIN_VALUE;
        int m = foregroundAppList.size();
        int n = backgroundAppList.size();
        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0) {
            int sum = foregroundAppList.get(i).get(1) + backgroundAppList.get(j).get(1);

            if (sum > deviceCapacity) {
                --j;

            } else {
                if (max <= sum) {
                    if (max < sum) {
                        max = sum;
                        result.clear();
                    }

                    List<Integer> temp = new ArrayList<>();
                    temp.add(foregroundAppList.get(i).get(0));
                    temp.add(backgroundAppList.get(j).get(0));
                    result.add(temp);

                    int index = j - 1;

                    while (index >= 0 && backgroundAppList.get(index).get(1).equals(backgroundAppList.get(index + 1).get(1))) {
                        List<Integer> temp2 = new ArrayList<>();
                        temp2.add(foregroundAppList.get(i).get(0));
                        temp2.add(backgroundAppList.get(j).get(0));
                        result.add(temp2);

                        result.add(temp2);
                    }
                }
                ++i;
            }
        }
        return result;
    }


}
