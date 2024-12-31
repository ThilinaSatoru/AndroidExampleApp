package sajana.assignment.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import sajana.assignment.app.data.Product;
import sajana.assignment.app.ui.AboutFragment;
import sajana.assignment.app.ui.AddProductFragment;
import sajana.assignment.app.ui.HelpFragment;
import sajana.assignment.app.ui.LoginFragment;
import sajana.assignment.app.ui.DashboardFragment;
import sajana.assignment.app.ui.ViewProductFragment;

public class MainActivity extends AppCompatActivity {

    private final List<Product> productList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load LoginFragment initially
        loadFragment(new LoginFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.menu_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (item.getItemId() == R.id.menu_about) {
                selectedFragment = new AboutFragment();
            } else if (item.getItemId() == R.id.menu_help) {
                selectedFragment = new HelpFragment();
            }
            else if (item.getItemId() == R.id.menu_add_product) {
                selectedFragment = new AddProductFragment();
            } else if (item.getItemId() == R.id.menu_view_products) {
                selectedFragment = new ViewProductFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, fragment)
                .commit();
    }


    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}