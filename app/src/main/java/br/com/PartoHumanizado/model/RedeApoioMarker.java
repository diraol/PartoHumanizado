/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.model;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by sergio holanda on 25-Nov-14.
 */
public class RedeApoioMarker {

    public static List<RedeApoioMarker> readFromAssets(Context context) {
        CsvAssetReader csvAssetReader = new CsvAssetReader(context);
        csvAssetReader.read();
        return csvAssetReader.getObjects();
    }

    private LatLng latLng;

    private String title;

    private String endereco;

    private String telefone;

    private BitmapDescriptor icon;

    private MarkerOptions markerOptions;

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    public void setIcon(BitmapDescriptor icon) {
        this.icon = icon;
    }

    private static final class CsvAssetReader extends ModelCsvAssetReader<RedeApoioMarker> {

        public CsvAssetReader(Context context) {
            super(context, "rede_apoio.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected RedeApoioMarker getObject(String[] split) {
            RedeApoioMarker redeApoioMarker = new RedeApoioMarker();
            redeApoioMarker.latLng = new LatLng(formatDouble(split[1]),formatDouble(split[2]));
            redeApoioMarker.title = split[0];
            redeApoioMarker.endereco = split[3];
            redeApoioMarker.telefone=split[4];
            return redeApoioMarker;
        }
    }

    private static double formatDouble(String numberString){
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = null;
        try {
            number = format.parse(numberString);
            double d = number.doubleValue();
            return d;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return 0;
    }
}
