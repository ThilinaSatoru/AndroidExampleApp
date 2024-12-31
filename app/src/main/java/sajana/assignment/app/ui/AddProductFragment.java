package sajana.assignment.app.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import sajana.assignment.app.MainActivity;
import sajana.assignment.app.R;
import sajana.assignment.app.data.Product;

public class AddProductFragment extends Fragment {

    private EditText productNameEditText, productDescriptionEditText, productPriceEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        productNameEditText = view.findViewById(R.id.edit_product_name);
        productDescriptionEditText = view.findViewById(R.id.edit_product_description);
        productPriceEditText = view.findViewById(R.id.edit_product_price);
        Button addProductButton = view.findViewById(R.id.button_add_product);

        addProductButton.setOnClickListener(v -> {
            String name = productNameEditText.getText().toString().trim();
            String description = productDescriptionEditText.getText().toString().trim();
            String price = productPriceEditText.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || TextUtils.isEmpty(price)) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double priceValue = Double.parseDouble(price);
                    Product product = new Product(name, description, priceValue);

                    ((MainActivity) requireActivity()).addProduct(product); // Add product to the shared list
                    Toast.makeText(getActivity(), "Product added successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid price value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void clearFields() {
        productNameEditText.setText("");
        productDescriptionEditText.setText("");
        productPriceEditText.setText("");
    }
}