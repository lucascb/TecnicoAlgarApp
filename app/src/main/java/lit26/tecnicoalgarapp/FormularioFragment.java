package lit26.tecnicoalgarapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lucas on 04/03/17.
 */

public class FormularioFragment extends Fragment {

     public View onCreateView (LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
         View view = layoutInflater.inflate(R.layout.fragment_formulario, viewGroup, false);

         return view;
     }

}
