/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.util.TextAssetReader;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 27/11/14.
 */
public class InformationFragment extends BaseFragment {

    private static final String TAG = "InformationFragment";
    public static final String KEY_ASSET_FILENAME = "KEY_ASSET_FILENAME";
//    public static final String KEY_TITTLE = "KEY_TITTLE";

    private String tittleStr;
    private String textStr;
    private String menuTittle;
    private String imageFile;

    @InjectView(R.id.titulo)
    TextView titulo;
    @InjectView(R.id.texto)
    TextView texto;
    @InjectView(R.id.image)
    ImageView image;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        tittleStr = getArguments().getString(KEY_TITTLE);
        String filename = getArguments().getString(KEY_ASSET_FILENAME);
//        Log.d(TAG, "onAttach filename: " + filename);
        TextAssetReader textAssetReader = new TextAssetReader(activity, filename);
        textAssetReader.setLineBreaker("<br/>");
        textAssetReader.read();

        imageFile = textAssetReader.getImage();
        tittleStr = textAssetReader.getTittle();
//        Log.d(TAG, "onAttach tittleStr " + tittleStr);
        textStr = textAssetReader.getString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, null);
        ButterKnife.inject(this, view);
        titulo.setText(tittleStr);
        texto.setText(Html.fromHtml(textStr));
        if (imageFile != null && !imageFile.isEmpty()) {
            new LoadImageAsyncTask(getActivity(), imageFile, image).execute();
        } else {
            image.setVisibility(View.GONE);
        }
        return view;
    }

    private class LoadImageAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private Context context;
        private String imageFile;
        private ImageView image;

        private LoadImageAsyncTask(Context context, String imageFile, ImageView image) {
            this.context = context;
            this.imageFile = imageFile;
            this.image = image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (imageFile == null)
                image.setVisibility(View.GONE);
            else
                image.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            InputStream is = null;
            try {
                is = context.getAssets().open(imageFile);
                return BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e(TAG, "doInBackground ", e);
            }
            cancel(true);
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null)
                image.setImageBitmap(bitmap);
        }
    }


    @Override
    public String getTitle() {
        return menuTittle;
    }

    public static final InformationFragment create(String assetFileName, String menuTitle) {
        InformationFragment fragment = new InformationFragment();
        fragment.menuTittle = menuTitle;
        Bundle bundle = new Bundle();
//        bundle.putString(KEY_TITTLE, tittleStr);
        bundle.putString(KEY_ASSET_FILENAME, assetFileName);
        fragment.setArguments(bundle);
        return fragment;
    }
}
