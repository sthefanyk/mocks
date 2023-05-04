package ifpr.pgua.eic.tvs.mocks;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CarteiraTest {

    @Test
    public void verificaTamanhoCarteira(){
        Acao acao = new Acao("0x3456", "SpaceX", 10);
        Acao acao2 = new Acao("0x2345", "SpaceY", 12);

        List<Acao> acoes = new ArrayList<>();
        acoes.add(acao);
        acoes.add(acao2);

        Carteira carteira = new Carteira(null);
        carteira.setCarteira(acoes);

        int tamanhoObtido = carteira.tamanhoCarteira();

        assertNotNull(carteira.getCarteira());
        assertEquals(22, tamanhoObtido);
    }

    @Test
    public void builderTamanhoCarteira(){
        Acao acao = AcaoBuilder.umaAcao().comQuantidade(10).agora();
        Acao acao2 = AcaoBuilder.umaAcao().comQuantidade(12).agora();

        List<Acao> acoes = new ArrayList<>();
        acoes.add(acao);
        acoes.add(acao2);

        Carteira carteira = new Carteira(null);
        carteira.setCarteira(acoes);

        int tamanhoObtido = carteira.tamanhoCarteira();

        assertNotNull(carteira.getCarteira());
        assertEquals(22, tamanhoObtido);
    }

    @Test
    public void verificaValorMercado(){
        double valorEsperado = 130;

        AcaoServiceStub acaoService = new AcaoServiceStub();

        Map<String,Double> bancoAcoes = new HashMap<>();
        bancoAcoes.put("Testla", 15.0);
        bancoAcoes.put("Alphabet", 25.0);
        bancoAcoes.put("Microsoft", 10.0);
        bancoAcoes.put("Meta", 8.0);

        acaoService.setBancoAcoes(bancoAcoes);

        Carteira carteira = new Carteira(acaoService);

        List<Acao> acoes = new ArrayList<>();
        acoes.add(AcaoBuilder.umaAcao().comNomeEmpresa("Testla").comQuantidade(2).agora());
        acoes.add(AcaoBuilder.umaAcao().comNomeEmpresa("Alphabet").comQuantidade(2).agora());
        acoes.add(AcaoBuilder.umaAcao().comNomeEmpresa("Microsoft").comQuantidade(1).agora());
        acoes.add(AcaoBuilder.umaAcao().comNomeEmpresa("Meta").comQuantidade(5).agora());

        carteira.setCarteira(acoes);

        double valorObtido = carteira.calculaValorMercado();

        assertNotNull(carteira.getCarteira());
        assertEquals(valorEsperado, valorObtido);
    }


}
