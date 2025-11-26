import { useState } from 'react'
import { HashRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './shared/components/layout/Navbar';

import './App.css'
import ConfiguracaoScreen from './screen/ConfiguracaoScreen';
import ConciliacoesScreen from './screen/ConciliacoesScreen';
import ServiceDashboard from './screen/ServiceDashboardScreen';
import { ServicesProvider } from './shared/context/ServicesContext';

function App() {

  return (
   <div className="min-h-screen bg-gray-100">
      <ServicesProvider>
        <Router>
          <Navbar />
          
          <div className="container mx-auto p-4 md:p-8">
            <Routes>
              <Route path="/" element={<ConciliacoesScreen />}/>
              <Route path="/conciliacoes" element={<ConciliacoesScreen />}  />
              <Route path="/integrador"  element={<ServiceDashboard></ServiceDashboard>} />
              <Route path="/configuracao" element={<ConfiguracaoScreen />} />
            </Routes>
          </div>
        </Router>
      </ServicesProvider>
    </div>
  )
}

export default App
