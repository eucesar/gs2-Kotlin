package br.com.github.eucesar.gsAndroid.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.github.eucesar.gsAndroid.calcularImc
import br.com.github.eucesar.gsAndroid.determinarClassificacaoIMC
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun CalculadoraImcScreen(modifier: Modifier = Modifier, navController: NavController) {
    var nome = remember { mutableStateOf("") }
    var peso = remember { mutableStateOf("") }
    var altura = remember { mutableStateOf("") }
    var imc = remember { mutableStateOf(0.0) }
    var statusImc = remember { mutableStateOf("") }
    var resultadoNome = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF329F6B))
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Calculadora IMC",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Seu nome",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF329F6B)
                    )
                    OutlinedTextField(
                        value = nome.value,
                        onValueChange = { nome.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Seu nome")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFF329F6B),
                            focusedBorderColor = Color(0xFF329F6B)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Seu peso",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF329F6B)
                    )
                    OutlinedTextField(
                        value = peso.value,
                        onValueChange = { peso.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Seu peso em Kg.")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFF329F6B),
                            focusedBorderColor = Color(0xFF329F6B)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sua altura",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF329F6B)
                    )
                    OutlinedTextField(
                        value = altura.value,
                        onValueChange = { altura.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Sua altura em cm.")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFF329F6B),
                            focusedBorderColor = Color(0xFF329F6B)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (peso.value.isNotEmpty() && altura.value.isNotEmpty() && nome.value.isNotEmpty()) {
                                imc.value = calcularImc(
                                    altura = altura.value.toDouble(),
                                    peso = peso.value.toDouble()
                                )
                                statusImc.value = determinarClassificacaoIMC(imc.value)
                                resultadoNome.value = nome.value
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF329F6B)
                        )
                    ) {
                        Text(
                            text = "Calcular",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Resultado",
                        color = Color(0xFF329F6B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    if (imc.value > 0) {
                        Text(
                            text = "Nome: ${resultadoNome.value}",
                            color = Color(0xFF329F6B),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = statusImc.value,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF329F6B),
                                    fontSize = 18.sp
                                )
                            }
                            Text(
                                text = String.format("%.1f", imc.value),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF329F6B),
                                fontSize = 32.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "Voltar",
                    fontSize = 18.sp,
                    color = Color(0xFF329F6B),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

