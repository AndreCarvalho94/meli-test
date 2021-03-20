package br.com.acdev.melisimian.components;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class Sha256HashCalculador implements CalculadorDeHash {

    @Override
    public String executar(String src) {
        return Hashing.sha256()
                .hashString(src, StandardCharsets.UTF_8)
                .toString();
    }
}
