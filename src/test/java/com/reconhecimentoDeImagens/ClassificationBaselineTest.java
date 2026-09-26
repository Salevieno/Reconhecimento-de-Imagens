package com.reconhecimentoDeImagens;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClassificationBaselineTest {

    @Test
    void mapsNetworkOutputToNearestCategory() {
        assertEquals("Telhado", ReconhecimentoDeImagens.CategorizeOutput(0.0));
        assertEquals("Rua", ReconhecimentoDeImagens.CategorizeOutput(0.25));
        assertEquals("AreaVerde", ReconhecimentoDeImagens.CategorizeOutput(0.5));
        assertEquals("Sombra", ReconhecimentoDeImagens.CategorizeOutput(0.75));
        assertEquals("Luz", ReconhecimentoDeImagens.CategorizeOutput(1.0));
    }

    @Test
    void preservesCurrentChoiceAtCategoryBoundary() {
        assertEquals("Rua", ReconhecimentoDeImagens.CategorizeOutput(0.125));
    }

    @Test
    void preservesCurrentNetworkPredictionsForRepresentativeColors() {
        assertEquals("Sombra", ReconhecimentoDeImagens.TrainedANNForwardPropagation(new double[] {128, 64, 32}));
        assertEquals("Sombra", ReconhecimentoDeImagens.TrainedANNForwardPropagation(new double[] {0, 0, 0}));
        assertEquals("Luz", ReconhecimentoDeImagens.TrainedANNForwardPropagation(new double[] {255, 255, 255}));
        assertEquals("AreaVerde", ReconhecimentoDeImagens.TrainedANNForwardPropagation(new double[] {30, 140, 50}));
    }
}