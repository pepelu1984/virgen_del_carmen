package com.dynamicdroides.virgendelcarmen.manager;

public enum Comportamientos
{
  ENSALADA,  PLATO1,  PLATO2,  PLATOS,  POSTRE_S,  COMEDOR,  CONTENTO,  CONTENTOTEXTO,  SUENNO,  SUENNOTEXTO,  FILA,  ESFINTER,  ESFINTERTEXTO,  JUEGOS,  ASEO,  ASEOTEXTO,  ROPA,  ROPATEXTO,  OBSERVACIONES;
  
  public String toString()
  {
    switch (this)
    {
    case ASEO: 
      return "ENSALADA";
    case ASEOTEXTO: 
      return "PLATO1";
    case ENSALADA: 
      return "COMEDOR";
    case COMEDOR: 
      return "PLATO2";
    case CONTENTO: 
      return "PLATOS";
    case CONTENTOTEXTO: 
      return "POSTRE";
    case ESFINTER: 
      return "CONTENTO";
    case ROPATEXTO: 
      return "ROPACAMBIO";
    case SUENNO: 
      return "ROPACAMBIOTEXTO";
    case OBSERVACIONES: 
      return "FILA";
    case ESFINTERTEXTO: 
      return "CONTENTOTEXTO";
    case FILA: 
      return "SUENNO";
    case JUEGOS: 
      return "SUENNOTEXTO";
    case PLATO1: 
      return "ESFINTER";
    case PLATO2: 
      return "ESFINTERTEXTO";
    case POSTRE_S: 
      return "BOLSAASEO";
    case ROPA: 
      return "BOLSALASEOTEXTO";
    case SUENNOTEXTO: 
      return "OBSERVACIONES";
    }
    return super.toString();
  }
}
