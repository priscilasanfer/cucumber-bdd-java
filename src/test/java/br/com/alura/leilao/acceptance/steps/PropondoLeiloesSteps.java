package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.eo.Do;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropondoLeiloesSteps {

    private Lance lance;
    private Leilao leilao;
    private ArrayList<Lance> lista;

    @Before
    public void setUp() {
        this.lista = new ArrayList<Lance>();
        leilao = new Leilao("Tablet XPTO");
    }

    @Dado("um lance valido")
    public void um_lance_valido() {
        lance = new Lance(new Usuario("fulano"), BigDecimal.TEN);
    }

    @Quando("eu proponho um lance no leilao")
    public void eu_proponho_o_lance() {
        leilao.propoe(lance);
    }

    @Entao("o lance e aceito")
    public void o_lance_e_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());

    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
        lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
        lista.add(lance);
    }

    @Quando("eu proponho varios lances no leilao")
    public void eu_proponho_varios_lances_no_leilao() {
        lista.forEach(lance -> leilao.propoe(lance));
    }

    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        assertEquals(this.lista.size(), leilao.getLances().size());
        assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
        assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

    @Dado("um lance invalido de {double} e do usuario {string}")
    public void um_lance_invalido_de(Double valor, String usuario) {
        System.out.println(usuario);
        lance = new Lance(new BigDecimal(valor));
    }

    @Entao("o lance nao e aceito")
    public void o_lance_nao_e_aceito() {
        assertEquals(0, leilao.getLances().size());
    }

    @Entao("o segundo lance nao e aceito")
    public void o_segundo_lance_nao_e_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> valores = dataTable.asMaps();

        for (Map<String, String> mapa : valores) {

            String valor = mapa.get("valor");
            String usuario = mapa.get("usuario");
            Lance lance = new Lance(new Usuario(usuario), new BigDecimal(valor));
            lista.add(lance);
        }

    }
}
