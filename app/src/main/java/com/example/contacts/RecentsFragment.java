package com.example.contacts;//package com.example.contacts;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class RecentsFragment extends Fragment {
//    private RecyclerView todayRecyclerView;
//    private RecyclerView yesterdayRecyclerView;
//    private RecyclerView olderRecyclerView;
//    private ContactsDatabaseManager databaseManager;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recents, container, false);
//
//        todayRecyclerView = view.findViewById(R.id.today_recycler_view);
//        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        yesterdayRecyclerView = view.findViewById(R.id.yesterday_recycler_view);
//        yesterdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        olderRecyclerView = view.findViewById(R.id.older_recycler_view);
//        olderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        databaseManager = new ContactsDatabaseManager(getContext()).open();
//
//        // Fetch recent call logs with contact details
//        List<CallLogWithContact> recentCallLogsWithContact = databaseManager.getCallLogsForAllContacts();
//
//        // Separate call logs based on last call date
//        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> olderCallLogs = new ArrayList<>();
//
//        // Adjust the date comparison logic based on your requirements
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            // Assuming callDate is a String in the format "yyyy-MM-dd"
//            String callDate = callLog.getCallDate();
//
//            // Implement your logic to categorize call logs into "today," "yesterday," or "older"
//            if (isToday(callDate)) {
//                todayCallLogs.add(callLog);
//            } else if (isYesterday(callDate)) {
//                yesterdayCallLogs.add(callLog);
//            } else {
//                olderCallLogs.add(callLog);
//            }
//        }
//
//        // Create adapters and set them to the corresponding RecyclerViews
//        todayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(todayCallLogs)));
//        yesterdayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(yesterdayCallLogs)));
//        olderRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(olderCallLogs)));
//
//        return view;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//
//    // Implement date comparison logic here
//    private boolean isToday(String date) {
//        // Implement logic to check if the date is today
//        return false;
//    }
//
//    private boolean isYesterday(String date) {
//        // Implement logic to check if the date is yesterday
//        return false;
//    }
//
//    // Extract contact names from a list of CallLogWithContact
//    private List<String> getContactNames(List<CallLogWithContact> callLogs) {
//        List<String> contactNames = new ArrayList<>();
//        for (CallLogWithContact callLog : callLogs) {
//            contactNames.add(callLog.getContactName());
//        }
//        return contactNames;
//    }
//}


//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.text.ParseException;
//
//public class RecentsFragment extends Fragment {
//    private RecyclerView todayRecyclerView;
//    private RecyclerView yesterdayRecyclerView;
//    private RecyclerView olderRecyclerView;
//    private ContactsDatabaseManager databaseManager;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recents, container, false);
//
//        todayRecyclerView = view.findViewById(R.id.today_recycler_view);
//        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        yesterdayRecyclerView = view.findViewById(R.id.yesterday_recycler_view);
//        yesterdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        olderRecyclerView = view.findViewById(R.id.older_recycler_view);
//        olderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        databaseManager = new ContactsDatabaseManager(getContext()).open();
//
//        // Fetch recent call logs with contact details
//        List<CallLogWithContact> recentCallLogsWithContact = databaseManager.getCallLogsForAllContacts();
//
//        // Separate call logs based on last call date
//        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> olderCallLogs = new ArrayList<>();
//
//        // Get the current date
//        Calendar currentCalendar = Calendar.getInstance();
//        Date currentDate = currentCalendar.getTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Adjust the date comparison logic based on your requirements
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            // Assuming callDate is a String in the format "yyyy-MM-dd"
//            String callDate = callLog.getCallDate();
//
//            try {
//                Date callDateObj = dateFormat.parse(callDate);
//                if (isSameDay(callDateObj, currentDate)) {
//                    todayCallLogs.add(callLog);
//                } else if (isYesterday(callDateObj, currentDate)) {
//                    yesterdayCallLogs.add(callLog);
//                } else {
//                    olderCallLogs.add(callLog);
//                }
//            } catch (ParseException e) {
//                // Handle parsing exception
//            }
//        }
//
//        // Create adapters and set them to the corresponding RecyclerViews
//        todayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(todayCallLogs)));
//        yesterdayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(yesterdayCallLogs)));
//        olderRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(olderCallLogs)));
//
//        return view;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//
//    // Compare two dates to check if they are on the same day
//    private boolean isSameDay(Date date1, Date date2) {
//        if (date1 == null || date2 == null) {
//            return false; // Handle null dates gracefully
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Compare two dates to check if date1 is one day before date2
//    private boolean isYesterday(Date date1, Date date2) {
//
//        if (date1 == null || date2 == null) {
//            return false; // Handle null dates gracefully
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        calendar1.add(Calendar.DAY_OF_YEAR, 1); // Add one day to date1
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Extract contact names from a list of CallLogWithContact
//    private List<String> getContactNames(List<CallLogWithContact> callLogs) {
//        List<String> contactNames = new ArrayList<>();
//        for (CallLogWithContact callLog : callLogs) {
//            contactNames.add(callLog.getContactName());
//        }
//        return contactNames;
//    }
//}
//
//
//
//
//
//
//
//
//









//
//
//import android.os.Bundle;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import androidx.fragment.app.Fragment;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//        import java.text.SimpleDateFormat;
//        import java.util.ArrayList;
//        import java.util.Calendar;
//        import java.util.Date;
//        import java.util.List;
//        import java.text.ParseException;
//
//public class RecentsFragment extends Fragment {
//    private RecyclerView todayRecyclerView;
//    private RecyclerView yesterdayRecyclerView;
//    private RecyclerView olderRecyclerView;
//    private ContactsDatabaseManager databaseManager;
//    private RecentContactAdapter adapter;
//    private List<CallLogWithContact> recentCallLogsWithContact;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recents, container, false);
//
//        todayRecyclerView = view.findViewById(R.id.today_recycler_view);
//        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        yesterdayRecyclerView = view.findViewById(R.id.yesterday_recycler_view);
//        yesterdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        olderRecyclerView = view.findViewById(R.id.older_recycler_view);
//        olderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        databaseManager = new ContactsDatabaseManager(getContext()).open();
//
//        // Fetch recent call logs with contact details
//        recentCallLogsWithContact = databaseManager.getCallLogsForAllContacts();
//
//        // Separate call logs based on last call date
//        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> olderCallLogs = new ArrayList<>();
//
//        // Get the current date
//        Calendar currentCalendar = Calendar.getInstance();
//        Date currentDate = currentCalendar.getTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Adjust the date comparison logic based on your requirements
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            String callDate = callLog.getCallDate();
//
//            if (callDate != null && !callDate.isEmpty()) {
//                try {
//                    Date callDateObj = dateFormat.parse(callDate);
//                    if (isSameDay(callDateObj, currentDate)) {
//                        todayCallLogs.add(callLog);
//                    } else if (isYesterday(callDateObj, currentDate)) {
//                        yesterdayCallLogs.add(callLog);
//                    } else {
//                        olderCallLogs.add(callLog);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    // Handle parsing exception
//                }
//            }
//        }
//
//        // Create adapters and set them to the corresponding RecyclerViews
//        todayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(todayCallLogs)));
//        yesterdayRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(yesterdayCallLogs)));
//        olderRecyclerView.setAdapter(new RecentContactAdapter(getContext(), getContactNames(olderCallLogs)));
//
//        return view;
//    }
//
//
//
//
//
//    public void searchContacts(String query) {
//        // Ensure recentCallLogsWithContact is not null
//        if (recentCallLogsWithContact != null) {
//            // Filter the recent call logs based on the query
//            List<CallLogWithContact> filteredCallLogs = filterCallLogs(query);
//
//            // Extract contact names from the filtered call logs
//            List<String> filteredContactNames = getContactNames(filteredCallLogs);
//
//            // Update the RecyclerView with the filtered data
//            adapter.setContacts(filteredContactNames);
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    private List<CallLogWithContact> filterCallLogs(String query) {
//        List<CallLogWithContact> filteredCallLogs = new ArrayList<>();
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            if (callLog.getContactName().toLowerCase().contains(query.toLowerCase())) {
//                filteredCallLogs.add(callLog);
//            }
//        }
//        return filteredCallLogs;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//
//    // Compare two dates to check if they are on the same day
//    private boolean isSameDay(Date date1, Date date2) {
//        if (date1 == null || date2 == null) {
//            return false;
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Compare two dates to check if date1 is one day before date2
//    private boolean isYesterday(Date date1, Date date2) {
//        if (date1 == null || date2 == null) {
//            return false;
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        calendar1.add(Calendar.DAY_OF_YEAR, 1); // Add one day to date1
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Extract contact names from a list of CallLogWithContact
//    private List<String> getContactNames(List<CallLogWithContact> callLogs) {
//        List<String> contactNames = new ArrayList<>();
//        for (CallLogWithContact callLog : callLogs) {
//            contactNames.add(callLog.getContactName());
//        }
//        return contactNames;
//    }
//}

//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.text.ParseException;
//
//public class RecentsFragment extends Fragment {
//    private RecyclerView todayRecyclerView;
//    private RecyclerView yesterdayRecyclerView;
//    private RecyclerView olderRecyclerView;
//    private ContactsDatabaseManager databaseManager;
//    private RecentContactAdapter adapter;
//    private List<CallLogWithContact> recentCallLogsWithContact;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recents, container, false);
//
//        todayRecyclerView = view.findViewById(R.id.today_recycler_view);
//        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        yesterdayRecyclerView = view.findViewById(R.id.yesterday_recycler_view);
//        yesterdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        olderRecyclerView = view.findViewById(R.id.older_recycler_view);
//        olderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        databaseManager = new ContactsDatabaseManager(getContext()).open();
//
//        // Fetch recent call logs with contact details
//        recentCallLogsWithContact = databaseManager.getCallLogsForAllContacts();
//
//        // Separate call logs based on the last call date
//        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
//        List<CallLogWithContact> olderCallLogs = new ArrayList<>();
//
//        // Get the current date
//        Calendar currentCalendar = Calendar.getInstance();
//        Date currentDate = currentCalendar.getTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Adjust the date comparison logic based on your requirements
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            String callDate = callLog.getCallDate();
//
//            if (callDate != null && !callDate.isEmpty()) {
//                try {
//                    Date callDateObj = dateFormat.parse(callDate);
//                    if (isSameDay(callDateObj, currentDate)) {
//                        todayCallLogs.add(callLog);
//                    } else if (isYesterday(callDateObj, currentDate)) {
//                        yesterdayCallLogs.add(callLog);
//                    } else {
//                        olderCallLogs.add(callLog);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    // Handle parsing exception
//                }
//            }
//        }
//
//        // Create adapters and set them to the corresponding RecyclerViews
//        adapter = new RecentContactAdapter(getContext(), getContactNames(todayCallLogs));
//        todayRecyclerView.setAdapter(adapter);
//
//        adapter = new RecentContactAdapter(getContext(), getContactNames(yesterdayCallLogs));
//        yesterdayRecyclerView.setAdapter(adapter);
//
//        adapter = new RecentContactAdapter(getContext(), getContactNames(olderCallLogs));
//        olderRecyclerView.setAdapter(adapter);
//
//        return view;
//    }
//
//    public void searchContacts(String query) {
//        // Ensure recentCallLogsWithContact and adapters are not null
//        if (recentCallLogsWithContact != null) {
//            // Filter the recent call logs based on the query
//            List<CallLogWithContact> filteredCallLogs = filterCallLogs(query);
//
//            // Extract contact names from the filtered call logs
//            List<String> filteredContactNames = getContactNames(filteredCallLogs);
//
//            // Update each RecyclerView with the filtered data
//            adapter.setContacts(filteredContactNames);
//            adapter.notifyDataSetChanged();
//
//            // Update the "today" RecyclerView
//            if (todayRecyclerView != null) {
//                RecentContactAdapter todayAdapter = (RecentContactAdapter) todayRecyclerView.getAdapter();
//                if (todayAdapter != null) {
//                    todayAdapter.setContacts(filteredContactNames);
//                    todayAdapter.notifyDataSetChanged();
//                }
//            }
//
//            // Update the "yesterday" RecyclerView
//            if (yesterdayRecyclerView != null) {
//                RecentContactAdapter yesterdayAdapter = (RecentContactAdapter) yesterdayRecyclerView.getAdapter();
//                if (yesterdayAdapter != null) {
//                    yesterdayAdapter.setContacts(filteredContactNames);
//                    yesterdayAdapter.notifyDataSetChanged();
//                }
//            }
//
//            // Update the "older" RecyclerView
//            if (olderRecyclerView != null) {
//                RecentContactAdapter olderAdapter = (RecentContactAdapter) olderRecyclerView.getAdapter();
//                if (olderAdapter != null) {
//                    olderAdapter.setContacts(filteredContactNames);
//                    olderAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }
//
//
//    private List<CallLogWithContact> filterCallLogs(String query) {
//        List<CallLogWithContact> filteredCallLogs = new ArrayList<>();
//        for (CallLogWithContact callLog : recentCallLogsWithContact) {
//            if (callLog.getContactName().toLowerCase().contains(query.toLowerCase())) {
//                filteredCallLogs.add(callLog);
//            }
//        }
//        return filteredCallLogs;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//
//    // Compare two dates to check if they are on the same day
//    private boolean isSameDay(Date date1, Date date2) {
//        if (date1 == null || date2 == null) {
//            return false;
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Compare two dates to check if date1 is one day before date2
//    private boolean isYesterday(Date date1, Date date2) {
//        if (date1 == null || date2 == null) {
//            return false;
//        }
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(date1);
//        calendar1.add(Calendar.DAY_OF_YEAR, 1); // Add one day to date1
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }
//
//    // Extract contact names from a list of CallLogWithContact
//    private List<String> getContactNames(List<CallLogWithContact> callLogs) {
//        List<String> contactNames = new ArrayList<>();
//        for (CallLogWithContact callLog : callLogs) {
//            contactNames.add(callLog.getContactName());
//        }
//        return contactNames;
//    }
//}




import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class RecentsFragment extends Fragment {
    private RecyclerView todayRecyclerView;
    private RecyclerView yesterdayRecyclerView;
    private RecyclerView olderRecyclerView;
    private ContactsDatabaseManager databaseManager;
    private RecentContactAdapter todayAdapter;
    private RecentContactAdapter yesterdayAdapter;
    private RecentContactAdapter olderAdapter;
    private List<CallLogWithContact> recentCallLogsWithContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recents, container, false);

        todayRecyclerView = view.findViewById(R.id.today_recycler_view);
        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        yesterdayRecyclerView = view.findViewById(R.id.yesterday_recycler_view);
        yesterdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        olderRecyclerView = view.findViewById(R.id.older_recycler_view);
        olderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseManager = new ContactsDatabaseManager(getContext()).open();

        // Fetch recent call logs with contact details
        recentCallLogsWithContact = databaseManager.getCallLogsForAllContacts();

        // Separate call logs based on the last call date
        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
        List<CallLogWithContact> olderCallLogs = new ArrayList<>();

        // Get the current date
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Adjust the date comparison logic based on your requirements
        for (CallLogWithContact callLog : recentCallLogsWithContact) {
            String callDate = callLog.getCallDate();

            if (callDate != null && !callDate.isEmpty()) {
                try {
                    Date callDateObj = dateFormat.parse(callDate);
                    if (isSameDay(callDateObj, currentDate)) {
                        todayCallLogs.add(callLog);
                    } else if (isYesterday(callDateObj, currentDate)) {
                        yesterdayCallLogs.add(callLog);
                    } else {
                        olderCallLogs.add(callLog);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle parsing exception
                }
            }
        }

        // Create adapters for each RecyclerView
        todayAdapter = new RecentContactAdapter(getContext(), getContactNames(todayCallLogs));
        yesterdayAdapter = new RecentContactAdapter(getContext(), getContactNames(yesterdayCallLogs));
        olderAdapter = new RecentContactAdapter(getContext(), getContactNames(olderCallLogs));

        // Set adapters for each RecyclerView
        todayRecyclerView.setAdapter(todayAdapter);
        yesterdayRecyclerView.setAdapter(yesterdayAdapter);
        olderRecyclerView.setAdapter(olderAdapter);

        return view;
    }


    // Filter call logs based on the query
    private List<CallLogWithContact> filterCallLogs(String query) {
        List<CallLogWithContact> filteredCallLogs = new ArrayList<>();
        for (CallLogWithContact callLog : recentCallLogsWithContact) {
            if (callLog.getContactName().toLowerCase().contains(query.toLowerCase())) {
                filteredCallLogs.add(callLog);
            }
        }
        return filteredCallLogs;
    }

    public void searchContacts(String query) {
        // Ensure recentCallLogsWithContact and adapter are not null
        if (recentCallLogsWithContact != null) {
            // Filter the recent call logs based on the query
            List<CallLogWithContact> filteredCallLogs = filterCallLogs(query);

            // Extract contact names from the filtered call logs
            List<String> filteredContactNames = getContactNames(filteredCallLogs);

            // Update the RecyclerViews with the filtered data
            todayAdapter.setContacts(getContactNames(filterCallLogsForToday(filteredCallLogs)));
            yesterdayAdapter.setContacts(getContactNames(filterCallLogsForYesterday(filteredCallLogs)));
            olderAdapter.setContacts(filteredContactNames);

            todayAdapter.notifyDataSetChanged();
            yesterdayAdapter.notifyDataSetChanged();
            olderAdapter.notifyDataSetChanged();
        }
    }

    // Filter call logs for today
    private List<CallLogWithContact> filterCallLogsForToday(List<CallLogWithContact> callLogs) {
        List<CallLogWithContact> todayCallLogs = new ArrayList<>();
        // Get the current date
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (CallLogWithContact callLog : callLogs) {
            String callDate = callLog.getCallDate();
            if (callDate != null && !callDate.isEmpty()) {
                try {
                    Date callDateObj = dateFormat.parse(callDate);
                    if (isSameDay(callDateObj, currentDate)) {
                        todayCallLogs.add(callLog);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle parsing exception
                }
            }
        }
        return todayCallLogs;
    }

    // Filter call logs for yesterday
    private List<CallLogWithContact> filterCallLogsForYesterday(List<CallLogWithContact> callLogs) {
        List<CallLogWithContact> yesterdayCallLogs = new ArrayList<>();
        // Get the current date
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (CallLogWithContact callLog : callLogs) {
            String callDate = callLog.getCallDate();
            if (callDate != null && !callDate.isEmpty()) {
                try {
                    Date callDateObj = dateFormat.parse(callDate);
                    if (isYesterday(callDateObj, currentDate)) {
                        yesterdayCallLogs.add(callLog);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle parsing exception
                }
            }
        }
        return yesterdayCallLogs;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }

    // Compare two dates to check if they are on the same day
    private boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    // Compare two dates to check if date1 is one day before date2
    private boolean isYesterday(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.add(Calendar.DAY_OF_YEAR, 1); // Add one day to date1
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    // Extract contact names from a list of CallLogWithContact
    private List<String> getContactNames(List<CallLogWithContact> callLogs) {
        List<String> contactNames = new ArrayList<>();
        for (CallLogWithContact callLog : callLogs) {
            contactNames.add(callLog.getContactName());
        }
        return contactNames;
    }
    //this is comment
}
